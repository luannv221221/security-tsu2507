package com.ra.service.impl;

import com.ra.model.dto.DataResponse;
import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.LoginResponseDTO;
import com.ra.model.dto.user.RegisterRequestDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public DataResponse register(RegisterRequestDTO registerRequestDTO) {
        // gan quyen hien tai cho tai khoan dang ky la CUSTOMER
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.getRolesByRoleName("CUSTOMER");
        roles.add(role);
        // Convert DTO -> ENTITY
        User user = User.builder()
                .username(registerRequestDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .fullName(registerRequestDTO.getFullName())
                .status(true)
                .build();
        userRepository.save(user);
        return new DataResponse("Registerd Successfully",201);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getUsername(), loginRequestDTO.getPassword()
        ));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return LoginResponseDTO.builder()
                .username(userPrinciple.getUsername())
                .fullName(userPrinciple.getUser().getFullName())
                .typeToken("Bearer")
                .token(jwtProvider.generateToken(userPrinciple))
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }
}

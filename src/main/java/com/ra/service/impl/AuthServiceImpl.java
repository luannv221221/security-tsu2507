package com.ra.service.impl;

import com.ra.dto.DataResponse;
import com.ra.dto.user.LoginRequestDTO;
import com.ra.dto.user.LoginResponseDTO;
import com.ra.dto.user.RegisterRequestDTO;
import com.ra.entity.Role;
import com.ra.entity.User;
import com.ra.mapper.user.UserMapper;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.config.security.UserPrinciple;
import com.ra.config.security.jwt.JwtProvider;
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

        userRepository.save(UserMapper.toEntity(registerRequestDTO));
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

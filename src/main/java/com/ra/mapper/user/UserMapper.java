package com.ra.mapper.user;

import com.ra.dto.user.RegisterRequestDTO;
import com.ra.entity.Role;
import com.ra.entity.User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class UserMapper {
    @Autowired
    private static RoleRepository roleRepository;
    public static User toEntity(RegisterRequestDTO registerRequestDTO){
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.getRolesByRoleName("CUSTOMER");
        roles.add(role);
        return  User.builder()
                .username(registerRequestDTO.getUsername())
                .password(new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .fullName(registerRequestDTO.getFullName())
                .status(true)
                .build();
    }
}

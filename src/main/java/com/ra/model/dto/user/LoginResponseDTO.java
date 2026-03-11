package com.ra.model.dto.user;

import com.ra.model.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponseDTO {
    private String username;
    private String fullName;
    private String typeToken;
    private String token;
    private Set<Role> roles;
}

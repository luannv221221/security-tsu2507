package com.ra.service;

import com.ra.dto.DataResponse;
import com.ra.dto.user.LoginRequestDTO;
import com.ra.dto.user.LoginResponseDTO;
import com.ra.dto.user.RegisterRequestDTO;

public interface AuthService {
    DataResponse register(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}

package com.ra.service;

import com.ra.model.dto.DataResponse;
import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.LoginResponseDTO;
import com.ra.model.dto.user.RegisterRequestDTO;

public interface AuthService {
    DataResponse register(RegisterRequestDTO registerRequestDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}

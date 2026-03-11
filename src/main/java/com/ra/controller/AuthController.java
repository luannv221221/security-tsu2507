package com.ra.controller;

import com.ra.dto.DataResponse;
import com.ra.dto.user.LoginRequestDTO;
import com.ra.dto.user.LoginResponseDTO;
import com.ra.dto.user.RegisterRequestDTO;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        return new ResponseEntity<>("Profile", HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        DataResponse dataResponse =  authService.register(registerRequestDTO);
        return new ResponseEntity<>(dataResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }
}

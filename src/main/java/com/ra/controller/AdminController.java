package com.ra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @GetMapping("/api/v1/admin")
    public ResponseEntity<?> getAdmin(){
        return new ResponseEntity<>("Admin", HttpStatus.OK);
    }
}

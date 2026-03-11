package com.ra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/api/v1/home")
    public ResponseEntity<?> getHome(){
        return new ResponseEntity<>("Home", HttpStatus.OK);
    }
}

package com.agora.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agora.dto.auth.AuthSigninDTO;
import com.agora.dto.auth.AuthSignupDTO;
import com.agora.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authS;
    
    @PostMapping("/signup")
    public ResponseEntity<String> Signup(@RequestBody @Valid AuthSignupDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado conta");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> Signin(@RequestBody @Valid AuthSigninDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authS.Signin(dto));
    }

}

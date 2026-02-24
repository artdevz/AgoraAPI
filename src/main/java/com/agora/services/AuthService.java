package com.agora.services;

import org.springframework.stereotype.Service;

import com.agora.auth.LocalAuthenticationProvider;
import com.agora.dto.auth.AuthSigninDTO;
import com.agora.dto.auth.AuthSignupDTO;
import com.agora.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtService jwtS;
    private final LocalAuthenticationProvider localProvider;
    
    public String Signup(AuthSignupDTO dto) {
        return "";
    }

    public String Signin(AuthSigninDTO dto) {
        User user = localProvider.Authenticate(dto);
        return jwtS.GenerateAccessToken(user);
    }

}

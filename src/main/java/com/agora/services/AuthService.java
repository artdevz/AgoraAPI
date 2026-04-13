package com.agora.services;

import org.springframework.stereotype.Service;

import com.agora.auth.LocalAuthenticationProvider;
import com.agora.dto.auth.AuthResponseDTO;
import com.agora.dto.auth.AuthSigninDTO;
import com.agora.dto.auth.AuthSignupDTO;
import com.agora.dto.user.UserCreateDTO;
import com.agora.enums.AuthProvider;
import com.agora.models.User;
import com.agora.security.CustomUserDetails;
import com.agora.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final LocalAuthenticationProvider localProvider;
    
    public AuthResponseDTO Signup(AuthSignupDTO dto) {
        User user = userService.Create(new UserCreateDTO(
            dto.nickname(),
            dto.email(),
            dto.password(),
            AuthProvider.LOCAL
        ));

        CustomUserDetails userDetails = new CustomUserDetails(
            user.GetEmail(),
            user.GetPassword(),
            user.GetID(),
            user.GetNickname(),
            user.GetStatus()
        );

        return new AuthResponseDTO(jwtProvider.GenerateToken(userDetails));
    }

    public AuthResponseDTO Signin(AuthSigninDTO dto) {
        User user = localProvider.Authenticate(dto);

        CustomUserDetails userDetails = new CustomUserDetails(
            user.GetEmail(),
            user.GetPassword(),
            user.GetID(),
            user.GetNickname(),
            user.GetStatus()
        );

        return new AuthResponseDTO(jwtProvider.GenerateToken(userDetails));
    }

}

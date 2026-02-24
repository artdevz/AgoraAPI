package com.agora.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agora.dto.auth.AuthSigninDTO;
import com.agora.enums.AuthProvider;
import com.agora.interfaces.AuthenticationProvider;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LocalAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userR;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User Authenticate(Object request) {
        AuthSigninDTO dto = (AuthSigninDTO) request;

        User user = userR.findByEmailAndProvider(dto.email(), AuthProvider.LOCAL).map(u -> UserMapper.toDomain(u, false)).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials"));

        if (!passwordEncoder.matches(dto.password(), user.GetPassword())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");

        return user;
    }
    
}

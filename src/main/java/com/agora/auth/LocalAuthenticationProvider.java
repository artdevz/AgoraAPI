package com.agora.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

        User user = UserMapper.toDomain(userR.findByEmailAndProvider(dto.email(), AuthProvider.LOCAL).get(), false);

        if (!passwordEncoder.matches(dto.password(), user.GetPassword())) throw new RuntimeException("Invalid Password");

        return user;
    }
    
}

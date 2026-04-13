package com.agora.security;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userR;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userR.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
            user.getEmail(),
            user.getPassword(),
            user.getId(),
            user.getNickname(),
            user.getStatus()
        );
    }

    public CustomUserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        var user = userR.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
            user.getEmail(),
            user.getPassword(),
            user.getId(),
            user.getNickname(),
            user.getStatus()
        );
    }    

}

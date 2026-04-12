package com.agora.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agora.dto.user.UserCreateDTO;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User Create(UserCreateDTO dto) {
        User user = new User(
            null, // ID
            dto.nickname(),
            dto.email(),
            passwordEncoder.encode(dto.password()),
            LocalDate.now(),
            dto.provider()
        );
        return UserMapper.ToDomain(userRepository.save(UserMapper.ToEntity(user)));
    }

    public List<User> ReadAll() {
        return userRepository.findAll().stream().map(UserMapper::ToDomain).toList();
    }

    public User ReadByID(UUID id) {
        return UserMapper.ToDomain(userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User não encontrado")));
    }

    public User ReadByEmail(String email) {
        return UserMapper.ToDomain(userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("User não encontrado")));
    }

    public User ReadByNickname(String nickname) {
        return UserMapper.ToDomain(
            userRepository.findById(
                userRepository.findByNickname(
                    nickname).orElseThrow(() -> new IllegalArgumentException("User não encontrado")).getId()
                ).orElseThrow(() -> new IllegalArgumentException("User não encontrado"))
            );
    }

}

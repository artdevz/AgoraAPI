package com.agora.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agora.dto.user.UserCreateDTO;
import com.agora.dto.user.UserResponseDTO;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userR;
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
        return UserMapper.toDomain(userR.save(UserMapper.toEntity(user)), false);
    }

    public List<UserResponseDTO> ReadAll() {
        return userR.findAll().stream().map(UserMapper::ToResponseDTO).toList();
    }

}

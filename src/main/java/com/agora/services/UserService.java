package com.agora.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.agora.dto.user.UserCreateDTO;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userR;
    
    public String Create(UserCreateDTO dto) {
        User user = new User(
            null, // ID
            dto.username(),
            dto.email(),
            dto.password(),
            LocalDate.now()
        );
        return "User criado: " + userR.save(UserMapper.toEntity(user)).getId();
    }

    public String ReadAll() {
        return "";
    }

}

package com.agora.services;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agora.dto.user.UserCreateDTO;
import com.agora.dto.user.UserUpdateDTO;
import com.agora.enums.UserStatus;
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
            OffsetDateTime.now(),
            dto.provider(),
            UserStatus.ACTIVE
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

    // Sem ID pois somente o Usuário poderá alterar email/senha
    public void Update(UserUpdateDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ReadByEmail(auth.getName());

        if (user.GetStatus() == UserStatus.BANNED) return;

        user.SetPassword(passwordEncoder.encode(dto.password()));

        userRepository.save(UserMapper.ToEntity(user));
    }

    // BAN ou Tirar Suspensão (Menos de 30 Dias)
    public void UpdateStatus(UUID id) {}

    // Sem ID pois somente o Usuário poderá deletar sua conta (Será permanentemente suspensa o acesso em 30 Dias)
    public void Delete() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ReadByEmail(auth.getName());

        user.SetStatus(UserStatus.SUSPENDED);

        userRepository.save(UserMapper.ToEntity(user));
    }

}

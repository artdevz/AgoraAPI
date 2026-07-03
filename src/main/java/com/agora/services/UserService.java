package com.agora.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.agora.collectors.UserCollection;
import com.agora.dto.user.UserCreateDTO;
import com.agora.dto.user.UserUpdateDTO;
import com.agora.entities.UserEntity;
import com.agora.enums.UserRole;
import com.agora.enums.UserStatus;
import com.agora.interfaces.UserIterator;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.RoleRepository;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User Create(UserCreateDTO dto) {
        User user = User.builder()
            .id(null)
            .nickname(dto.nickname())
            .email(dto.email())
            .password(passwordEncoder.encode(dto.password()))
            .createdAt(OffsetDateTime.now())
            .provider(dto.provider())
            .status(UserStatus.ACTIVE)
        .build();
        return UserMapper.ToDomain(userRepository.save(UserMapper.ToEntity(user)));
    }

    public List<User> ReadAll() {
        return userRepository.findAll().stream().map(UserMapper::ToDomain).toList();
    }

    public List<User> ReadActives() {
        List<User> users = userRepository.findAll().stream().map(UserMapper::ToDomain).toList();

        UserCollection userCollection = new UserCollection();
        for (User user : users) userCollection.Add(user);

        UserIterator iterator = userCollection.ActiveUsersIterator();

        List<User> activeUsers = new ArrayList<>();
        
        while (iterator.HasNext()) {
            activeUsers.add(iterator.Next());
        }

        return (activeUsers);
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

    // Sem ID pois somente o Usuário poderá deletar sua conta (Será permanentemente suspensa o acesso em 30 Dias)
    public void Delete() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ReadByEmail(auth.getName());

        user.SetStatus(UserStatus.SUSPENDED);

        userRepository.save(UserMapper.ToEntity(user));
    }

    /*
    UserRole é um enum, como pretendo ter somente 3 cargos, User, Mod e Admin, isso serve:
    É recebido do front-end User | Mod, caso seja recebido Mod, é adicionado nas roles de Usuário
    Caso seja recebido User, então Mod é removido das roles do Usuário
    */
    public void UpdateRole(UUID id, UserRole role) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (role == UserRole.MOD) {
            user.getRoles().add(roleRepository.findByName("MOD").get());
        }

        if (role == UserRole.USER) {
            user.getRoles().remove(roleRepository.findByName("MOD").get());
        }

        userRepository.save(user);
    }

    @Transactional
    public void Ban(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = ReadByEmail(auth.getName());

        if (userEntity.getId().equals(user.GetID())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não pode banir a si mesmo");

        userEntity.setStatus(UserStatus.BANNED);
    }

    @Transactional
    public void Active(UUID id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setStatus(UserStatus.ACTIVE);
    }

}

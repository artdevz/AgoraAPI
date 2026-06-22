package com.agora.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.agora.entities.UserMuteEntity;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.MuteRepository;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MuteService {
    
    private final MuteRepository muteRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    
    public void Mute(UUID userID) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.ReadByEmail(auth.getName());
        
        if (user.GetID() == userID) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é permitido silenciar a si mesmo");

        UserMuteEntity mute = new UserMuteEntity();
        mute.setMuter(UserMapper.ToEntity(user));
        mute.setMuted(userRepository.findById(userID).get()); // To-Do: Fazer uma Exception caso não haja user com esse ID

        muteRepository.save(mute);
    }

    public void Unmute(UUID userID) {}

}

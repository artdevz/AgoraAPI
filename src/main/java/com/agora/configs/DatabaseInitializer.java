package com.agora.configs;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.agora.entities.RoleEntity;
import com.agora.entities.UserEntity;
import com.agora.enums.AuthProvider;
import com.agora.enums.UserStatus;
import com.agora.mappers.UserMapper;
import com.agora.models.User;
import com.agora.repositories.RoleRepository;
import com.agora.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(List.of(
                new RoleEntity("ADMIN"),
                new RoleEntity("MOD")
                // new RoleEntity("USER")
            ));
        }

        if (userRepository.findByNickname("AgoraAdmin").isEmpty()) {
            User admin = User.builder()
                .nickname("AgoraAdmin")
                .email("agora@gmail.com")
                .password(passwordEncoder.encode("4bcdefG!"))
                .createdAt(OffsetDateTime.now())
                .status(UserStatus.ACTIVE)
                .provider(AuthProvider.LOCAL)
            .build();

            UserEntity adminEntity = UserMapper.ToEntity(admin);
            adminEntity.getRoles().add(roleRepository.findByName("ADMIN").get());

            userRepository.save(adminEntity);
        }

    }    

}

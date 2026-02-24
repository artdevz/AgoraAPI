// package com.agora.security;

// import java.io.IOException;
// import java.time.LocalDate;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;

// import com.agora.enums.AuthProvider;
// import com.agora.mappers.UserMapper;
// import com.agora.models.User;
// import com.agora.repositories.UserRepository;
// import com.agora.services.JwtService;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Component
// public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    
//     private final UserRepository userR;
//     private final JwtService jwtS;

//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//         OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

//         String email = oauthUser.getAttribute("email");
//         String googleID = oauthUser.getAttribute("sub");

//         User user = userR
//             .findByProviderAndProviderID(AuthProvider.GOOGLE, googleID)
//             .map(entity -> UserMapper.toDomain(entity, false))
//             .orElseGet(() -> Register(email, googleID));

//         String token = jwtS.GenerateAccessToken(user);

//         response.sendRedirect("http://localhost:4200/oauth-success?token=" + token);
//     }

//     private User Register(String email, String googleID) {
//         User user = new User(
//             null,
//             email,
//             email,
//             null,
//             LocalDate.now(),
//             AuthProvider.GOOGLE
//         );

//         user.SetProviderID(googleID);

//         return UserMapper.toDomain(userR.save(UserMapper.toEntity(user)), false);
//     }

// }

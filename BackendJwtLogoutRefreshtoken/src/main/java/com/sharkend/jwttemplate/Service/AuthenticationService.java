package com.sharkend.jwttemplate.Service;

import com.sharkend.jwttemplate.Dto.Auth.AuthenticationDto;
import com.sharkend.jwttemplate.Dto.Auth.AuthenticationResponseDto;
import com.sharkend.jwttemplate.Dto.Auth.RegisterDto;
import com.sharkend.jwttemplate.Entity.Token.Token;
import com.sharkend.jwttemplate.Entity.Token.TokenType;
import com.sharkend.jwttemplate.Entity.User.Role;
import com.sharkend.jwttemplate.Entity.User.User;
import com.sharkend.jwttemplate.Repository.TokenRepository;
import com.sharkend.jwttemplate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .tokenType(TokenType.BEARER)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true); //todo remove and create pull request after changing TokenRepository
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public AuthenticationResponseDto register(RegisterDto request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }

    public AuthenticationResponseDto authenticate(AuthenticationDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        //successfully authenticated:
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user); //disallowing concurrently valid tokens (single login only)
        saveUserToken(user, jwtToken);
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }
}

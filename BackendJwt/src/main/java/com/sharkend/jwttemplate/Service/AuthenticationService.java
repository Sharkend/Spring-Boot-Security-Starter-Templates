package com.sharkend.jwttemplate.Service;

import com.sharkend.jwttemplate.Dto.Auth.AuthenticationDto;
import com.sharkend.jwttemplate.Dto.Auth.AuthenticationResponseDto;
import com.sharkend.jwttemplate.Dto.Auth.RegisterDto;
import com.sharkend.jwttemplate.Entity.Role;
import com.sharkend.jwttemplate.Entity.User;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponseDto register(RegisterDto request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
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
        return AuthenticationResponseDto.builder().token(jwtToken).build();
    }
}

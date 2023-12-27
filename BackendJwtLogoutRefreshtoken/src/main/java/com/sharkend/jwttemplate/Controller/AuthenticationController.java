package com.sharkend.jwttemplate.Controller;

import com.sharkend.jwttemplate.Dto.Auth.AuthenticationDto;
import com.sharkend.jwttemplate.Dto.Auth.AuthenticationResponseDto;
import com.sharkend.jwttemplate.Dto.Auth.RegisterDto;
import com.sharkend.jwttemplate.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

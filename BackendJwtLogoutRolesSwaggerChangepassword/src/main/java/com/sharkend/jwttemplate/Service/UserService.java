package com.sharkend.jwttemplate.Service;

import com.sharkend.jwttemplate.Dto.ChangePasswordDto;
import com.sharkend.jwttemplate.Entity.User.User;
import com.sharkend.jwttemplate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void changePassword(ChangePasswordDto request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();

        if (passwordEncoder.matches(request.getCurrentPassword(), user.getPassword()))
            throw new IllegalStateException("Current Password is incorrect");

        if (! request.getNewPassword().equals(request.getConfirmationPassword()))
            throw new IllegalStateException("New password does not match its confirmation");

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}

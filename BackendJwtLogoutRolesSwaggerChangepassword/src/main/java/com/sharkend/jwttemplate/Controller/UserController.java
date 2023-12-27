package com.sharkend.jwttemplate.Controller;

import com.sharkend.jwttemplate.Dto.ChangePasswordDto;
import com.sharkend.jwttemplate.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordDto request,
            Principal connectedUser
    ) {
        userService.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
}

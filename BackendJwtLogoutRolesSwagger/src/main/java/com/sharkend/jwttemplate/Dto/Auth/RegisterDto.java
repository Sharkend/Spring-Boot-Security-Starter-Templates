package com.sharkend.jwttemplate.Dto.Auth;

import com.sharkend.jwttemplate.Entity.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}

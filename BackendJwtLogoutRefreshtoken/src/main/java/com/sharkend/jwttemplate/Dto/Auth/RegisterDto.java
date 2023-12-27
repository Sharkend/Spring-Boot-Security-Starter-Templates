package com.sharkend.jwttemplate.Dto.Auth;

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
}

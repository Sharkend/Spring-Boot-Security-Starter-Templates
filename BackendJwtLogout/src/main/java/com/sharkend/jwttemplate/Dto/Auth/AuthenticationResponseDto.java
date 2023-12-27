package com.sharkend.jwttemplate.Dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {
    @JsonProperty("access_token") //// Maps field "accessToken" to "access_token" in the JSON
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
}

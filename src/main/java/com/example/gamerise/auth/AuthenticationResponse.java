package com.example.gamerise.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("refresh_token")
    private String refreshToken;
}

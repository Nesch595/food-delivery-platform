package org.example.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JwtResponse {

    private String type = "Bearer";
    private String accessToken;
    private String refreshToken;

    public JwtResponse(@JsonProperty("accessToken") String accessToken,
                       @JsonProperty("refreshToken") String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

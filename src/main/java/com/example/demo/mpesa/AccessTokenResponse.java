package com.example.demo.mpesa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private String expiresIn;

    @SneakyThrows //handles checked exceptions
    @Override
    public String toString() {
       // try {
            return new ObjectMapper().writeValueAsString(this);
        //} catch (JsonProcessingException e) {
         //   throw new RuntimeException(e);
        //}
    }
}

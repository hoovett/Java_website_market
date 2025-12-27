package com.example.First_website.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponseDTO {
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("token_type")
    private String tokenType = "Bearer";
    
    // Default constructor for JSON deserialization
    public LoginResponseDTO() {}
    
    // Parameterized constructor
    public LoginResponseDTO(String token) {
        this.token = token;
    }
    
    // Getters
    public String getToken() {
        return token;
    }
    
    public String getTokenType() {
        return tokenType;
    }
}

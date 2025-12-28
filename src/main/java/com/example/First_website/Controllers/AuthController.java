package com.example.First_website.Controllers;

import com.example.First_website.DTO.LoginRequestDTO;
import com.example.First_website.DTO.LoginResponseDTO;
import com.example.First_website.Security.User.UserPrincipal;
import com.example.First_website.Services.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(),
                        loginRequestDTO.getPassword()
                )
        );
        
        // Get the authenticated user's details
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        // Generate token with UserPrincipal
        String token = jwtService.generateToken(userPrincipal);
        
        return new LoginResponseDTO(token);
    }


}


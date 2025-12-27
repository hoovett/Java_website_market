package com.example.First_website.Security.User;

import com.example.First_website.DB_Entity.UserEntity;
import com.example.First_website.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        UserEntity user = userRepository.findByEmail(email)
             .orElseThrow(() -> new UsernameNotFoundException("User with this email is not found"));

        return new UserPrincipal(user);
    }
}

package com.example.test212.services.components;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    @Bean
    public PasswordEncoder mainEncoder(){
        return new BCryptPasswordEncoder();
    }
}

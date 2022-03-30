package com.example.test212.controllers;

import com.example.test212.controllers.models.RegistrationParamsRequest;
import com.example.test212.controllers.models.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @PostMapping("/signup")
    public UserDto signup(@RequestBody RegistrationParamsRequest params) {

        return null;
    }

}

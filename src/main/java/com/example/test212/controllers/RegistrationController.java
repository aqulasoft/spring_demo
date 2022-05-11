package com.example.test212.controllers;

import com.example.test212.controllers.exceptions.UserAlreadyExistException;
import com.example.test212.controllers.models.RegistrationParamsRequest;
import com.example.test212.controllers.models.UserDto;
import com.example.test212.services.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody RegistrationParamsRequest params) throws UserAlreadyExistException {
        return registrationService.signup(params);
    }
}

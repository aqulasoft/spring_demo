package com.example.test212.services;

import com.example.test212.controllers.exceptions.UserAlreadyExistException;
import com.example.test212.controllers.models.RegistrationParamsRequest;
import com.example.test212.controllers.models.UserDto;

public interface RegistrationService {

    UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException;
}

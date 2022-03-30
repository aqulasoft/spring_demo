package com.example.test212.services.impl;

import com.example.test212.controllers.exceptions.UserAlreadyExistException;
import com.example.test212.controllers.models.RegistrationParamsRequest;
import com.example.test212.controllers.models.UserDto;
import com.example.test212.database.entities.User;
import com.example.test212.database.repositories.UserRepository;
import com.example.test212.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(ModelMapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto signup(RegistrationParamsRequest registrationParamsRequest) throws UserAlreadyExistException {
        Optional<User> existedUser = userRepository.findOptionalByEmail(registrationParamsRequest.getEmail());
        existedUser.orElseThrow(UserAlreadyExistException::new);

        User user = mapper.map(registrationParamsRequest, User.class);
        String password = passwordEncoder.encode(user.getPassword() + "sada");

        user.setPassword(password);
        userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }
}

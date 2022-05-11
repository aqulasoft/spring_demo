package com.example.test212.controllers;

import com.example.test212.controllers.exceptions.StudentNotExistException;
import com.example.test212.controllers.models.UserDto;
import com.example.test212.database.entities.User;
import com.example.test212.database.repositories.UserRepository;
import com.example.test212.security.models.OurAuthToken;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/private/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") String id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(UserDto::new).orElse(null);
    }

    @GetMapping("/me")
    public UserDto getMyInfo(OurAuthToken ourAuthToken) {
        return new UserDto(ourAuthToken.getPrincipal());
    }


}

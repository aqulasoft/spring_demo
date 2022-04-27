package com.example.test212.controllers.models;

import com.example.test212.database.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class UserDto {

    private String id;
    private int age;
    private String name;

    private String email;

    public UserDto(User user) {
        id = user.getId();
        age = user.getAge();
        name = user.getName();
        email = user.getEmail();
    }
}

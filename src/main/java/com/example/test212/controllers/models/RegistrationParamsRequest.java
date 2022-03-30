package com.example.test212.controllers.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationParamsRequest {

    private int age;
    private String name;

    private String email;
    private String password;
}

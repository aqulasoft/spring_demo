package com.example.test212.services.components;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityMapper {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

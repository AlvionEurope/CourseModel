package me.rudnikov.backend.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    private final ModelMapper modelMapper;

    public ModelMapperConfiguration() {
        this.modelMapper = new ModelMapper();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

}
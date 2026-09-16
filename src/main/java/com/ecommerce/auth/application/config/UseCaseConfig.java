package com.ecommerce.auth.application.config;

import com.ecommerce.auth.domain.model.gateway.UserGateway;
import com.ecommerce.auth.domain.usecase.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UserUseCase userUseCase(UserGateway userGateway) {
        return new UserUseCase(userGateway);
    }
}

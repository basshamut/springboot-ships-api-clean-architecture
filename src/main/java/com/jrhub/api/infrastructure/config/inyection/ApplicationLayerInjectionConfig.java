package com.jrhub.api.infrastructure.config.inyection;

import com.jrhub.api.application.usecase.MovieSpaceShipUseCaseImpl;
import com.jrhub.api.application.usecase.UserUseCaseImpl;
import com.jrhub.api.infrastructure.messaging.rabbitmq.RabbitMQSenderUseCaseService;
import com.jrhub.api.infrastructure.persistance.repository.MovieSpaceShipSQLRepository;
import com.jrhub.api.infrastructure.persistance.repository.UserSQLRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationLayerInjectionConfig {

    @Bean
    public MovieSpaceShipUseCaseImpl movieSpaceShipUseCase(
            MovieSpaceShipSQLRepository movieSpaceShipSQLRepository,
            RabbitMQSenderUseCaseService rabbitMQSenderUseCaseService) {
        return new MovieSpaceShipUseCaseImpl(movieSpaceShipSQLRepository, rabbitMQSenderUseCaseService);
    }

    @Bean
    public UserUseCaseImpl userUseCase(UserSQLRepository userSQLRepository) {
        return new UserUseCaseImpl(userSQLRepository);
    }
}

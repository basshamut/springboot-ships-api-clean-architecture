package com.jrhub.api.infrastructure.messaging.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrhub.api.domain.usecase.alert.LoginAttendMessageSenderUseCase;
import com.jrhub.api.application.dto.LoginAttemptDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderUseCaseService implements LoginAttendMessageSenderUseCase<LoginAttemptDto> {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void loginAttempsSend(String topic, LoginAttemptDto message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(topic, jsonMessage);
            log.info("Message sent to topic {}: {}", topic, jsonMessage);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message to JSON: {}", e.getMessage());
            throw new RuntimeException("Failed to serialize message to JSON", e);
        }
    }
}

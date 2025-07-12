package com.jrhub.api.infrastructure.messaging.rabbitmq;

import com.jrhub.api.domain.model.AuditEvent;
import com.jrhub.api.domain.usecase.audit.AuditMessageSenderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.jrhub.api.application.dto.AuditEventDto;
import com.jrhub.api.application.mapper.AuditEventMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.jrhub.api.utils.Constants.AUDIT_QUEUE;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQSenderUseCaseService implements AuditMessageSenderUseCase<AuditEvent> {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendAuditMessage(AuditEvent message) {
        AuditEventDto dto = AuditEventMapper.MAPPER.toDto(message);
        rabbitTemplate.convertAndSend(AUDIT_QUEUE, dto);
    }
}

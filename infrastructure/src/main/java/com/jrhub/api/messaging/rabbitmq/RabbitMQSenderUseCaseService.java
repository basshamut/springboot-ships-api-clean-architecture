package com.jrhub.api.messaging.rabbitmq;

import com.jrhub.api.model.AuditEvent;
import com.jrhub.api.usecase.audit.AuditMessageSenderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.jrhub.api.dto.AuditEventDto;
import com.jrhub.api.mapper.AuditEventMapper;
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
        AuditEventDto dto = AuditEventMapper.INSTANCE.toDto(message);
        rabbitTemplate.convertAndSend(AUDIT_QUEUE, dto);
    }
}

package com.jrhub.api.messaging.rabbitmq;

import com.jrhub.api.usecase.audit.AuditMessageReceiverUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.jrhub.api.dto.AuditEventDto;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Getter
@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQReceiverUseCaseService implements AuditMessageReceiverUseCase<AuditEventDto> {
    private final CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void receiveAuditMessage(AuditEventDto message) {
        log.info("Received message: {}", message);
        latch.countDown();
    }
}

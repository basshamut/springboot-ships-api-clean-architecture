package com.jrhub.api.domain.usecase.audit;

public interface AuditMessageSenderUseCase<T> {
    void sendAuditMessage(T message);
}

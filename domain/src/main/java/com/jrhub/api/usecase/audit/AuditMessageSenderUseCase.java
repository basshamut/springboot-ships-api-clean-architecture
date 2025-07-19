package com.jrhub.api.usecase.audit;

public interface AuditMessageSenderUseCase<T> {
    void sendAuditMessage(T message);
}

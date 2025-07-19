package com.jrhub.api.usecase.audit;

public interface AuditMessageReceiverUseCase<T> {
    void receiveAuditMessage(T message);
}

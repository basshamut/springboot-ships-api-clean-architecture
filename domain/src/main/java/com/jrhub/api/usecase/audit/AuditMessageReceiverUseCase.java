package com.jrhub.api.domain.usecase.audit;

public interface AuditMessageReceiverUseCase<T> {
    void receiveAuditMessage(T message);
}

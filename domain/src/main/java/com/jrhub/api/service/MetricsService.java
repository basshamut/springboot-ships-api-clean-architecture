package com.jrhub.api.service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public interface MetricsService {
    void incrementSpaceShipCreated();
    void incrementSpaceShipUpdated();
    void incrementSpaceShipDeleted();
    void incrementSpaceShipRead();
    void incrementAuditMessagesSent();
    void incrementKafkaMessagesSent();
    void recordDatabaseOperationTime(long duration, TimeUnit timeUnit);
    void executeWithTracing(String operationName, Runnable operation);
    <T> T executeWithTracing(String operationName, Supplier<T> operation);
}
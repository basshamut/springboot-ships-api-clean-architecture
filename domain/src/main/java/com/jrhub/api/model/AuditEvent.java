package com.jrhub.api.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain Value Object for audit events
 */
public class AuditEvent {
    private final String user;
    private final String operation;
    private final Long shipId;
    private final String shipName;
    private final LocalDateTime timestamp;

    public AuditEvent(String user, String operation, Long shipId, String shipName, LocalDateTime timestamp) {
        this.user = user;
        this.operation = operation;
        this.shipId = shipId;
        this.shipName = shipName;
        this.timestamp = timestamp;
    }

    public static AuditEvent create(String user, String action, MovieSpaceShip ship) {
        return new AuditEvent(user, action, ship.getId(), ship.getName(), LocalDateTime.now());
    }

    public String getUser() {
        return user;
    }

    public String getOperation() {
        return operation;
    }

    public Long getShipId() {
        return shipId;
    }

    public String getShipName() {
        return shipName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditEvent that = (AuditEvent) o;
        return Objects.equals(user, that.user) &&
               Objects.equals(operation, that.operation) &&
               Objects.equals(shipId, that.shipId) &&
               Objects.equals(shipName, that.shipName) &&
               Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, operation, shipId, shipName, timestamp);
    }

    @Override
    public String toString() {
        return "AuditEvent{" +
                "user='" + user + '\'' +
                ", action='" + operation + '\'' +
                ", shipId=" + shipId +
                ", shipName='" + shipName + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

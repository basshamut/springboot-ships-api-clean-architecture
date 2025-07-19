package com.jrhub.api.dto;

import java.io.Serializable;

public record AuditEventDto(
    String operation,
    Long shipId,
    String shipName,
    String user,
    Long timestamp
) implements Serializable {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String operation;
        private Long shipId;
        private String shipName;
        private String user;
        private Long timestamp;

        public Builder operation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder shipId(Long shipId) {
            this.shipId = shipId;
            return this;
        }

        public Builder shipName(String shipName) {
            this.shipName = shipName;
            return this;
        }

        public Builder user(String user) {
            this.user = user;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public AuditEventDto build() {
            return new AuditEventDto(operation, shipId, shipName, user, timestamp);
        }
    }
}


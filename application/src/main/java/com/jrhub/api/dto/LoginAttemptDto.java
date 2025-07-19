package com.jrhub.api.dto;

import java.io.Serializable;

public record LoginAttemptDto(
    String username,
    String message,
    Integer attempts,
    Long timestamp
) implements Serializable {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String message;
        private Integer attempts;
        private Long timestamp;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder attempts(Integer attempts) {
            this.attempts = attempts;
            return this;
        }

        public Builder timestamp(Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public LoginAttemptDto build() {
            return new LoginAttemptDto(username, message, attempts, timestamp);
        }
    }
}

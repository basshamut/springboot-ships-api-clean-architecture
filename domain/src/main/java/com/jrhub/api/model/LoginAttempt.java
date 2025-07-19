package com.jrhub.api.model;

import java.io.Serializable;
import java.util.Objects;

public class LoginAttempt  implements Serializable {
    private String username;
    private String message;
    private Integer attempts;
    private Long timestamp;

    public LoginAttempt() {
    }

    public LoginAttempt(String username, String message, Integer attempts, Long timestamp) {
        this.username = username;
        this.message = message;
        this.attempts = attempts;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginAttempt that = (LoginAttempt) o;
        return Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getMessage(), that.getMessage()) && Objects.equals(getAttempts(), that.getAttempts()) && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getMessage(), getAttempts(), getTimestamp());
    }

    @Override
    public String toString() {
        return "LoginAttempt{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", attempts=" + attempts +
                ", timestamp=" + timestamp +
                '}';
    }
}

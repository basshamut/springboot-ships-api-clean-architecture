package com.jrhub.api.dto;

public record LoginRequestDto(
    String username,
    String password
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequestDto build() {
            return new LoginRequestDto(username, password);
        }
    }
}

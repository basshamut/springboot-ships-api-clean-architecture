package com.jrhub.api.dto;

public record LoginResponseDto(
    String type,
    String token
) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String token;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public LoginResponseDto build() {
            return new LoginResponseDto(type, token);
        }
    }
}

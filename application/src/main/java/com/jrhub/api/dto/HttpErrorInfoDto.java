package com.jrhub.api.dto;

import java.time.ZonedDateTime;

public record HttpErrorInfoDto(
    String timestamp,
    String path,
    Integer httpStatus,
    String message
) {

    public HttpErrorInfoDto(int httpStatus, String path, String message) {
        this(ZonedDateTime.now().toString(), path, httpStatus, message);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String timestamp;
        private String path;
        private Integer httpStatus;
        private String message;

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder httpStatus(Integer httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public HttpErrorInfoDto build() {
            return new HttpErrorInfoDto(timestamp, path, httpStatus, message);
        }
    }
}

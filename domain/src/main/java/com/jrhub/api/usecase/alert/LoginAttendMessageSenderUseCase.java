package com.jrhub.api.domain.usecase.alert;

public interface LoginAttendMessageSenderUseCase<T> {
    void loginAttempsSend(String topic, T message);
}

package com.jrhub.api.usecase.alert;

public interface LoginAttendMessageSenderUseCase<T> {
    void loginAttempsSend(String topic, T message);
}

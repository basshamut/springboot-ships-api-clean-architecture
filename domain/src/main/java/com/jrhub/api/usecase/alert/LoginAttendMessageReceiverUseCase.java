package com.jrhub.api.domain.usecase.alert;

public interface LoginAttendMessageReceiverUseCase<T> {
    void loginAttempsReceive(String message);
}

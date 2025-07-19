package com.jrhub.api.usecase.alert;

public interface LoginAttendMessageReceiverUseCase<T> {
    void loginAttempsReceive(String message);
}

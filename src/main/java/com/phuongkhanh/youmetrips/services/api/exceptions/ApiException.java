package com.phuongkhanh.youmetrips.services.api.exceptions;

public abstract class ApiException extends RuntimeException {
    public abstract String getMessage(final String language);
}

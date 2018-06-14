package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public abstract class PresentationException extends RuntimeException {
    private String _message;

    public PresentationException message(final String message) {
        _message = message;
        return this;
    }

    @Override
    public String getMessage() {
        return _message;
    }
}

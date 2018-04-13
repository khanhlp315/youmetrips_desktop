package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public abstract class PresentationException extends RuntimeException {
    public abstract String getMessage( final String language );
}

package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidJwtException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid Jwt Exception";
    }
}

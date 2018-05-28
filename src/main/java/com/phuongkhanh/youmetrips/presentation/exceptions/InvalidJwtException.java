package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidJwtException extends PresentationException {
    public InvalidJwtException()
    {
        message("Invalid Jwt Exception");
    }
}

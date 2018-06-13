package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class ExpiredJwtException extends PresentationException {
    public ExpiredJwtException()
    {
        message("Expired Token! Please, Click Resend Code.");
    }
}

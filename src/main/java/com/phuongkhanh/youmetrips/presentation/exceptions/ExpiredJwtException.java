package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class ExpiredJwtException extends PresentationException {
    public String getMessage()
    {
        return "Expired Token! Please, Click Resend Code.";
    }
}

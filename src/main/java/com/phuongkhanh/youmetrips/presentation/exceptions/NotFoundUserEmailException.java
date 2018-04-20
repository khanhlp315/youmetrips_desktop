package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class NotFoundUserEmailException extends PresentationException {
    public String getMessage()
    {
        return "Email is not found";
    }
}

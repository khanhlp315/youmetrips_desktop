package com.phuongkhanh.youmetrips.presentation.exceptions;


/*
 * @author by LeVoGiaKhang
 */
public class NotFoundUserEmailException extends PresentationException {
    public NotFoundUserEmailException()
    {
        message("Email is not found");
    }
}

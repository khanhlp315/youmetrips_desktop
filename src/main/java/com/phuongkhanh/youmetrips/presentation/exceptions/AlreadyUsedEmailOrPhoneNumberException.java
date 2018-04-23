package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class AlreadyUsedEmailOrPhoneNumberException extends PresentationException {
    public AlreadyUsedEmailOrPhoneNumberException()
    {
        message("Email or Phone has already");
    }
}

package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class AlreadyUsedEmailOrPhoneNumberException extends PresentationException {
    public String getMessage(String language) {
        return "Already Email or Phone";
    }
}

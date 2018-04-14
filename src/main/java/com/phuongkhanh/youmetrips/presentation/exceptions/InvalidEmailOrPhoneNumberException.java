package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidEmailOrPhoneNumberException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid Email or Phone";
    }
}

package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidEmailException extends PresentationException {
    @Override
    public String getMessage(String language) {
        return "Email or Phone Number is invalid";
    }
}

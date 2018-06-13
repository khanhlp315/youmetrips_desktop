package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidEmailOrPhoneNumberException extends PresentationException {
    public InvalidEmailOrPhoneNumberException() {
        message("Invalid Email or Phone");
    }
}

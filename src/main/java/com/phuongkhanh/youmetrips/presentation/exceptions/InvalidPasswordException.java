package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidPasswordException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid Password";
    }
}

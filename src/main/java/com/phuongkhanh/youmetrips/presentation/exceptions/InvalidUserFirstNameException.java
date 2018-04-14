package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidUserFirstNameException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid User First Name";
    }
}

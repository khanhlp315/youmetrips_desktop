package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidUserLastNameException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid User Last Name";
    }
}

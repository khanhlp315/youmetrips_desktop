package com.phuongkhanh.youmetrips.presentation.exceptions;


/*
 * @author by LeVoGiaKhang
 */
public class UserNotConfirmedException extends PresentationException {
    public String getMessage(String language) {
        return "User is not confirmed";
    }
}

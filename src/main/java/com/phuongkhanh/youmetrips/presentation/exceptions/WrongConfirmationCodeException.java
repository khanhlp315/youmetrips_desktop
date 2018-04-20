package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class WrongConfirmationCodeException extends PresentationException{
    public String getMessage(String language) {
        return "Wrong confirmation code";
    }
}

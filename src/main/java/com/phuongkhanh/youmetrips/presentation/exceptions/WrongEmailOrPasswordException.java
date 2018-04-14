package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class WrongEmailOrPasswordException extends PresentationException{
    public String getMessage(String language) {
        return "Wrong email or password";
    }
}

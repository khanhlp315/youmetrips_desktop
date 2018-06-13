package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class WrongEmailOrPasswordException extends PresentationException {
    public WrongEmailOrPasswordException() {
        message("Wrong email or password");
    }
}

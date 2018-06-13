package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class WrongConfirmationCodeException extends PresentationException {

    public WrongConfirmationCodeException() {
        message("Wrong confirmation code");
    }
}

package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class ConfirmPasswordNotMatchException extends PresentationException {

    public ConfirmPasswordNotMatchException(){
        message("Confirm password does not match");
    }
}

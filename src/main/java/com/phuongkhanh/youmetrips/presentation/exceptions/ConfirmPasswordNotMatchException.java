package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class ConfirmPasswordNotMatchException extends PresentationException {
    @Override
    public String getMessage(String language) {
        return "Confirm Password is not match";
    }
}

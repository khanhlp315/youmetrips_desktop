package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class EmptyFieldException extends PresentationException {
    @Override
    public String getMessage(String language) {
        return "Fill in all field";
    }
}

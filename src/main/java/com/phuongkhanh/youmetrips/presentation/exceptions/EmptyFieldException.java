package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class EmptyFieldException extends PresentationException {
    public EmptyFieldException() {
        message("Fill in all field");
    }
}

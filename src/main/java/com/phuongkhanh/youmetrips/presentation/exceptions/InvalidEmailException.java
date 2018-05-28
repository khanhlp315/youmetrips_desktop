package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidEmailException extends PresentationException {
    public InvalidEmailException(){
        message("Email or Phone Number is invalid");
    }
}

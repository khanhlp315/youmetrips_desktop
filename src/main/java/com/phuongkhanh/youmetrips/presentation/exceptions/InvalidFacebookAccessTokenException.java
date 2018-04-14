package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidFacebookAccessTokenException extends PresentationException {
    public String getMessage(String language) {
        return "Invalid Facebook Access Token";
    }
}

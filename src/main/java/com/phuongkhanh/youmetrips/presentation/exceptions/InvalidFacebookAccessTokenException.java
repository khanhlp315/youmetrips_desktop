package com.phuongkhanh.youmetrips.presentation.exceptions;

/*
 * @author by LeVoGiaKhang
 */
public class InvalidFacebookAccessTokenException extends PresentationException {

    public InvalidFacebookAccessTokenException()
    {
        message("Invalid Facebook Access Token");
    }
}

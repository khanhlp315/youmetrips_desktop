package com.phuongkhanh.youmetrips.presentation.exceptions;


/*
 * @author by LeVoGiaKhang
 */
public class UserNotConfirmedException extends PresentationException {
    public UserNotConfirmedException()
    {
        message("User is not confirmed");
    }

}

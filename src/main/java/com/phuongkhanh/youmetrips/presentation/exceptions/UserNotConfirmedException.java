package com.phuongkhanh.youmetrips.presentation.exceptions;


import com.phuongkhanh.youmetrips.services.api.models.SignUp;

/*
 * @author by LeVoGiaKhang
 */
public class UserNotConfirmedException extends PresentationException {
    private SignUp _signUp;

    public UserNotConfirmedException(SignUp signUp)
    {
        _signUp = signUp;
        message("User is not confirmed");
    }

    public SignUp getSignUp() {
        return _signUp;
    }
}

package com.phuongkhanh.youmetrips.services.stores;

/*
 * @author by LeVoGiaKhang
 */
public class AuthenticationStore
{
    private static final String USER_ID = "USER_ID";
    private static final String JWT = "JWT";
    private static final String CONFIRM_TOKEN = "CONFIRM_TOKEN";
    private static final String RESEND_CONFIRMATION_CODE_TOKEN = "RESEND_CONFIRMATION_CODE_TOKEN";
    private static final String RESET_PASSWORD_TOKEN = "RESET_PASSWORD_TOKEN";

    private Store _store;

    public AuthenticationStore(){
        _store = new Store();
    }

    @SuppressWarnings("unchecked")
    void storeLoginData(int userId, String jwt){
        _store.setItem(USER_ID, userId);
        _store.setItem(JWT, jwt);
    }

    @SuppressWarnings("unchecked")
    void storeSignupData(int userId, String confirmToken, String resendConfirmationCodeToken){
        _store.setItem(USER_ID, userId);
        _store.setItem(CONFIRM_TOKEN, confirmToken);
        _store.setItem(RESEND_CONFIRMATION_CODE_TOKEN, resendConfirmationCodeToken);
    }

    @SuppressWarnings("unchecked")
    void storeUserId(int userId){
        _store.setItem(USER_ID, userId);
    }

    @SuppressWarnings("unchecked")
    void storeJwt(String jwt){
        _store.setItem(JWT, jwt);
    }

    @SuppressWarnings("unchecked")
    void storeConfirmToken(String confirmToken){
        _store.setItem(CONFIRM_TOKEN, confirmToken);
    }

    @SuppressWarnings("unchecked")
    void storeResendConfirmationCodeToken(String resendConfirmationCodeToken){
        _store.setItem(RESEND_CONFIRMATION_CODE_TOKEN, resendConfirmationCodeToken);
    }

    @SuppressWarnings("unchecked")
    void storeResetPasswordToken(String resetPasswordToken){
        _store.setItem(RESET_PASSWORD_TOKEN, resetPasswordToken);
    }

    int getUserId(){
        return (int)_store.getItem(USER_ID);
    }

    String getJwt(){
        return (String)_store.getItem(JWT);
    }

    String getConfirmToken(){
        return (String)_store.getItem(CONFIRM_TOKEN);
    }

    String getResendConfirmationCodeToken(){
        return (String)_store.getItem(RESEND_CONFIRMATION_CODE_TOKEN);
    }

    String getResetPasswordToken(){
        return (String)_store.getItem(RESET_PASSWORD_TOKEN);
    }

    void addUserIdListener(StoreItem.Wrapper value){
    _store.addListener(USER_ID, value);
}

    void addJwtListener(StoreItem.Wrapper value){
    _store.addListener(JWT, value);
}

    void removeUserIdListener(StoreItem.Wrapper value){
    _store.removeListener(USER_ID, value);
}

    void removeJwtListener(StoreItem.Wrapper value){
    _store.removeListener(JWT, value);
}
}

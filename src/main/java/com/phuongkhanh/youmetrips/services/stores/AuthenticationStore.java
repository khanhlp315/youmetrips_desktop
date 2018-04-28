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
    public void storeLoginData(int userId, String jwt){
        _store.setItem(USER_ID, userId);
        _store.setItem(JWT, jwt);
    }

    /**
     * Save the state of the <tt>ArrayList</tt> instance to a stream (that
     * is, serialize it).
     *
     * @serialData The length of the array backing the <tt>ArrayList</tt>
     *             instance is emitted (int), followed by all of its elements
     *             (each an <tt>Object</tt>) in the proper order.
     */
    @SuppressWarnings("unchecked")
    public void storeSignupData(int userId, String confirmToken, String resendConfirmationCodeToken){
        _store.setItem(USER_ID, userId);
        _store.setItem(CONFIRM_TOKEN, confirmToken);
        _store.setItem(RESEND_CONFIRMATION_CODE_TOKEN, resendConfirmationCodeToken);
    }

    @SuppressWarnings("unchecked")
    public void storeUserId(int userId){
        _store.setItem(USER_ID, userId);
    }

    @SuppressWarnings("unchecked")
    public void storeJwt(String jwt){
        _store.setItem(JWT, jwt);
    }

    @SuppressWarnings("unchecked")
    public void storeConfirmToken(String confirmToken){
        _store.setItem(CONFIRM_TOKEN, confirmToken);
    }

    @SuppressWarnings("unchecked")
    public void storeResendConfirmationCodeToken(String resendConfirmationCodeToken){
        _store.setItem(RESEND_CONFIRMATION_CODE_TOKEN, resendConfirmationCodeToken);
    }

    @SuppressWarnings("unchecked")
    public void storeResetPasswordToken(String resetPasswordToken){
        _store.setItem(RESET_PASSWORD_TOKEN, resetPasswordToken);
    }

    public int getUserId(){
        return (int)_store.getItem(USER_ID);
    }

    public  String getJwt(){
        return (String)_store.getItem(JWT);
    }

    public String getConfirmToken(){
        return (String)_store.getItem(CONFIRM_TOKEN);
    }

    public String getResendConfirmationCodeToken(){
        return (String)_store.getItem(RESEND_CONFIRMATION_CODE_TOKEN);
    }

    public String getResetPasswordToken(){
        return (String)_store.getItem(RESET_PASSWORD_TOKEN);
    }

    public void addUserIdListener(StoreItem.Wrapper value){
    _store.addListener(USER_ID, value);
}

    public void addJwtListener(StoreItem.Wrapper value){
    _store.addListener(JWT, value);
}

    public void removeUserIdListener(StoreItem.Wrapper value){
    _store.removeListener(USER_ID, value);
}

    public void removeJwtListener(StoreItem.Wrapper value){
    _store.removeListener(JWT, value);
}
}

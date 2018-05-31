package com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code;

import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidJwtException;
import com.phuongkhanh.youmetrips.presentation.exceptions.WrongConfirmationCodeException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;

import javax.inject.Inject;

/*
 * @author by LeVoGiaKhang
 */
public class SignUpConfirmationCodePresenter extends PresenterBase<SignUpConfirmationCodeScreen> {
    private SignUpConfirmationCodeService _service;

    @Inject
    public SignUpConfirmationCodePresenter(SignUpConfirmationCodeService service)
    {
        _service = service;
    }


    //region SEND CONFIRMATION CODE
    /***************************************************************************************************
     ***************************************************************************************************
     ***************************************************************************************************/
    public void sendConfirmationCode(String confirmationCode)
    {
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doSendConfirmationCode(confirmationCode);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onSendConfirmationCodeSuccess();
                    }

                    @Override
                    protected void failed() {
                        onSendConfirmationCodeFailed(getException());
                    }
                }
        ).start();
    }

    private void doSendConfirmationCode(String confirmationCode)
    {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.sendConfirmationCode(confirmationCode, authenticationStore.getUserId(), authenticationStore.getConfirmToken());
    }

    private void onSendConfirmationCodeSuccess()
    {
        //TODO: catch exceptions when send verify code success
        getView().showSuccess("send confirmation code success");
        getView().onNavigateToHome();
    }

    private void onSendConfirmationCodeFailed(final Throwable e)
    {
        //TODO: catch exceptions when send verify code failed
        if(e instanceof InvalidJwtException)
        {
            getView().showError(e.getMessage());
        }
        else if(e instanceof WrongConfirmationCodeException)
        {
            getView().showError(e.getMessage());
        }
        else
        {
            getView().showError(e.getMessage());
        }
    }
    //endregion





    //region RESEND
    /***************************************************************************************************
     ***************************************************************************************************
     ***************************************************************************************************/
    public void resendCode()
    {
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doResendConfirmationCode();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onResendConfirmationCodeSuccess();
                    }

                    @Override
                    protected void failed() {
                        onResendConfirmationCodeFailed(getException());
                    }
                }
        ).start();
    }

    private void doResendConfirmationCode()
    {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        _service.resendConfirmationCode(authenticationStore.getUserId(), authenticationStore.getResendConfirmationCodeToken());
    }

    private void onResendConfirmationCodeSuccess() {
        getView().showSuccess("Resend Confirmation Code success");
    }

    private void onResendConfirmationCodeFailed(Throwable e) {
        if(e instanceof InvalidJwtException)
        {
            getView().showError(e.getMessage());
        }
        else
        {
            getView().showError(e.getMessage());
        }
    }
    //endregion
}

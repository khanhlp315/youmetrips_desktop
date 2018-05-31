package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.phuongkhanh.youmetrips.presentation.exceptions.WrongRecoveryCodeException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitCodePresenter extends PresenterBase<NewPasswordInitCodeScreen> {
    private NewPasswordInitCodeService _service;

    public NewPasswordInitCodePresenter(NewPasswordInitCodeService service) {
        _service = service;
    }


    //region SEND CODE
    public void sendCode(String recoveryCode) {
        getView().setLoading(true);

        new Thread(new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doSendCode(recoveryCode);
                return null;
            }

            @Override
            protected void succeeded() {
                onSendCodeSuccess();
                getView().setLoading(false);
            }



            @Override
            protected void failed() {
                onSendCodeFailed(getException());
                getView().setLoading(false);
            }

        }).start();
    }

    private void doSendCode(String recoveryCode) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        String token = _service.sendCodeToResetPassword(recoveryCode, authenticationStore.getUserId());
        authenticationStore.storeResetPasswordToken(token);
    }

    private void onSendCodeSuccess() {
        getView().navigateToInputNewPasswordScreen();
    }

    private void onSendCodeFailed(final Throwable e) {
        if (e instanceof WrongRecoveryCodeException) {
            getView().showError(e.getMessage());
        } else {
            getView().showError(e.getMessage());
        }
    }
    //endregion 


    //region RESEND CODE
    public void resendCode() {
        new Thread(new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doResendCode();
                return null;
            }

            @Override
            protected void succeeded() {
                onResendCodeSuccess();
            }

            ;

            @Override
            protected void failed() {
                onResendCodeFailed(getException());
            }

            ;
        }).start();
    }

    private void doResendCode() {
        _service.resendCodeToResetPassword();
    }

    private void onResendCodeSuccess() {
        getView().showSuccess("Resend code success");
    }

    private void onResendCodeFailed(final Throwable e) {
        getView().showSuccess("Resend code failed");
    }
    //endregion

}

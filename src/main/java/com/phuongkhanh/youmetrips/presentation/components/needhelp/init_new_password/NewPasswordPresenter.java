package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password;

import com.phuongkhanh.youmetrips.presentation.exceptions.ConfirmPasswordNotMatchException;
import com.phuongkhanh.youmetrips.presentation.exceptions.ExpiredJwtException;
import com.phuongkhanh.youmetrips.presentation.exceptions.InvalidPasswordException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import javafx.concurrent.Task;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordPresenter extends PresenterBase<NewPasswordScreen> {
    private NewPasswordService _service;

    public NewPasswordPresenter(NewPasswordService service)
    {
        _service = service;
    }

    public void sendNewPassword(String newPassword,String confirmPassword)
    {
        new Thread(new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doSendNewPassword(newPassword, confirmPassword);
                return null;
            }

            @Override
            protected void succeeded(){
                onSendNewPasswordSuccess();};

            @Override
            protected void failed(){
                onSendNewPasswordFailed(getException());};
        }).start();
    }

    private void doSendNewPassword(String newPassword, String confirmPassword)
    {
        if (!newPassword.equals(confirmPassword)) {
            throw new ConfirmPasswordNotMatchException();
        }

        _service.sendPasswordToResetPassword(newPassword);
    }

    private void onSendNewPasswordSuccess()
    {
        getView().showSuccess("Change password success \n Please log-in");
        getView().navigateToLoginScreen();
    }

    private void onSendNewPasswordFailed(final Throwable e)
    {
        if (e instanceof ConfirmPasswordNotMatchException)
        {
            getView().showError(e.getMessage());
        }
        else if(e instanceof ExpiredJwtException)
        {
            getView().showError(e.getMessage());
        }
        else if (e instanceof InvalidPasswordException)
        {
            getView().showError(e.getMessage());
        }
        else
        {
            getView().showError(e.getMessage());
        }
    }

}

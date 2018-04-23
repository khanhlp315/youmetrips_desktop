package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email;

import com.phuongkhanh.youmetrips.presentation.exceptions.NotFoundUserEmailException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import javafx.concurrent.Task;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitEmailPresenter extends PresenterBase<NewPasswordInitEmailScreen> {
    private NewPasswordInitEmailService _service;

    public NewPasswordInitEmailPresenter(NewPasswordInitEmailService service)
    {
        _service = service;
    }

    public void sendEmailToResetPassword(String email)
    {
        new Thread(new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doSendEmailToResetPassword(email);
                return null;
            }

            @Override
            protected void succeeded(){onSendEmailToResetPasswordSuccess();}

            @Override
            protected void failed(){onSendEmailToResetPasswordFailed(getException());}

            @Override
            protected void running(){
                onRunning();
            }
        }).start();
    }

    private void doSendEmailToResetPassword(String email)
    {
        _service.sendEmailToResetPassword(email);
    }

    private void onSendEmailToResetPasswordSuccess()
    {
        getView().navigateToInputCode();
    }

    private void onSendEmailToResetPasswordFailed(final Throwable e)
    {
        if(e instanceof NotFoundUserEmailException)
        {
            getView().showError(e.getMessage());
        }
        else
        {
            getView().showError(e.getMessage());
        }
    }

    private void onRunning(){
        getView().showLoading();
    }

}

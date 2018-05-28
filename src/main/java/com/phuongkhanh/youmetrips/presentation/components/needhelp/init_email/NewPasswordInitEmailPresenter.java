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

        getView().setLoading(true);

        new Thread(new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doSendEmailToResetPassword(email);
                return null;
            }

            @Override
            protected void succeeded(){onSendEmailToResetPasswordSuccess();
            getView().setLoading(false);}

            @Override
            protected void failed(){onSendEmailToResetPasswordFailed(getException());
                getView().setLoading(false);}
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
}

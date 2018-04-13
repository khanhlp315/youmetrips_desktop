package com.phuongkhanh.youmetrips.presentation.components.login;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.presentation.models.User;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.concurrent.Task;

import javax.inject.Inject;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.validateEmail;

public class LoginPresenter extends PresenterBase<LoginScreen> {
    private final LoginService _service;

    @Inject
    public LoginPresenter( LoginService service ) {
        _service = service;
    }

    public void requestToSignup() {
        getView().navigateToSignup();
    }

    public void loginWithEmailPassword(String email, String password){
        // cho view loading

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        doLogin( email, password );
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        onLoginSuccess();
                    }

                    @Override
                    protected void failed() {
                        onLoginFailed( getException() );
                    }
                }
        ).start();
    }

    private void doLogin(String email, String password){
        if ( !validateEmail( email ) ) {

            //throw new InvalidEmailException();
        }
        User user = _service.login( email, password );
    }

    private void onLoginFailed(final Throwable ex){
        // xu ly login fail
        //if(exception instanceof InvalidEmailException)...
    }

    private void onLoginSuccess(){
        // xu ly login thanh cong
    }
}

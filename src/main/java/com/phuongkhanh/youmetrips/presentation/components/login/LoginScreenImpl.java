package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenImpl extends FXMLScreen implements LoginScreen, Initializable {
    private final LoginPresenter _presenter;

    @FXML
    private JFXButton button1;

    public LoginScreenImpl( final LoginPresenter presenter ) {
        _presenter = presenter;
        _presenter.setView( this );
    }

    @Override
    public void navigateToSignup() {
        _presenter.requestToSignup();
    }

    @Override
    protected String fxmlPath() {
        return "/login.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loginWithEmailPassword()
    {
        _presenter.loginWithEmailPassword();
    }
}

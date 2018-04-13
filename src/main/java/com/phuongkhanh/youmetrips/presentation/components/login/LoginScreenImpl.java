package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenImpl extends FXMLScreen implements LoginScreen, Initializable {
    private final LoginPresenter _presenter;

    @FXML
    private JFXButton _btnCreateAccount;

    @FXML
    private JFXTextField _txtEmailOrPhone;

    @FXML
    private JFXTextField _txtPassword;

    @FXML
    private JFXTextField _txtConfirmPassword;

    @FXML
    private JFXTextField _txtFirstName;

    @FXML
    private JFXTextField _txtLastName;


    public LoginScreenImpl(final LoginPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
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

    public void loginWithEmailPassword() {
        // _presenter.loginWithEmailPassword();
    }
}

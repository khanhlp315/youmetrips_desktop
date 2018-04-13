package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenImpl extends FXMLScreen implements LoginScreen, Initializable {
    private final LoginPresenter _presenter;

    @FXML
    private JFXTextField _txtEmailOrPhoneLogin;
    @FXML
    private JFXPasswordField _txtPasswordLogin;
    @FXML
    private JFXTextField _txtEmailOrPhoneSignUp;
    @FXML
    private JFXPasswordField _txtPasswordSignUp;
    @FXML
    private JFXPasswordField _txtConfirmPassword;
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

    @FXML
    public void login() {
        _presenter.login(_txtEmailOrPhoneLogin.getText(), _txtPasswordLogin.getText());
    }

    @FXML
    public void loginWithFB() {
         _presenter.loginWithFB();
    }

    @FXML
    public void signUp() {
        _presenter.signUp(_txtEmailOrPhoneSignUp.getText(),
                _txtPasswordSignUp.getText(),
                _txtConfirmPassword.getText(),
                _txtFirstName.getText(),
                _txtLastName.getText());
    }



}

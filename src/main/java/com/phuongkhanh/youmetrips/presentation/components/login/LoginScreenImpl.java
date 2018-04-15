package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

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
    private JFXPasswordField _txtConfirmPasswordSignUp;
    @FXML
    private JFXTextField _txtFirstNameSignUp;
    @FXML
    private JFXTextField _txtLastNameSignUp;
    @FXML
    private JFXPasswordField _txtConfirmPasswordNewPassword;
    @FXML
    private JFXPasswordField _txtNewPassword;
    @FXML
    private JFXTextField _txtEmailOrPhoneHelp;
    @FXML
    private JFXTextField _txtVerifiedCode;


    public LoginScreenImpl(final LoginPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void navigateToSignup() {
        _presenter.requestToSignup();
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert( Alert.AlertType.WARNING );
        alert.setTitle( "Error" );
        alert.setContentText( message );
        alert.showAndWait();
    }

    @Override
    public void showSuccess(String message) {
        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.setTitle( "Success" );
        alert.setContentText( message );
        alert.showAndWait();
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected String fxmlPath() {
        return "/sign_in_main.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void login() {
        _presenter.login(_txtEmailOrPhoneSignUp.getText(), _txtPasswordSignUp.getText());
    }

    @FXML
    public void loginWithFB() {
         _presenter.loginWithFB();
    }

    @FXML
    public void signUp() {
        _presenter.signUp(_txtEmailOrPhoneSignUp.getText(),
                _txtPasswordSignUp.getText(),
                _txtConfirmPasswordSignUp.getText(),
                _txtFirstNameSignUp.getText(),
                _txtLastNameSignUp.getText());
    }



}

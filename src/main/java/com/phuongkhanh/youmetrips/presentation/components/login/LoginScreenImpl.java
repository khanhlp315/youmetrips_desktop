package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreenImpl;
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
        return "/sign_in.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void login() {
        System.out.println(_txtEmailOrPhoneLogin.getText() + "" + _txtPasswordLogin.getText());
        _presenter.login(_txtEmailOrPhoneLogin.getText(), _txtPasswordLogin.getText());
    }

    @FXML
    public void loginWithFB() {
         _presenter.loginWithFB();
    }

    @FXML
    public void navigateToSignUp()
    {
        // click button Create in sign_in.fxml
        navigate(SignUpScreenImpl.class);
    }

    @FXML
    public void needHelp() {
        navigate(NewPasswordInitEmailScreenImpl.class);
    }
}

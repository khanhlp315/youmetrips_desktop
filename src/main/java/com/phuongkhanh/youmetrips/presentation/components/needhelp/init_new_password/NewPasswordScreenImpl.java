package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.login.LoginScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordScreenImpl extends FXMLScreen implements NewPasswordScreen, Initializable {
    private NewPasswordPresenter _presenter;

    @FXML
    private JFXPasswordField _txtNewPassword;

    @FXML
    private JFXPasswordField _txtConfirmPassword;

    public NewPasswordScreenImpl(NewPasswordPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/sign_in_help_new_password.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    @FXML
    public void next()
    {
        _presenter.sendNewPassword(_txtNewPassword.getText(), _txtConfirmPassword.getText());
    }

    public void navigateToLoginScreen()
    {
        navigate(LoginScreenImpl.class);
    }
}

package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitCodeScreenImpl extends FXMLScreen implements NewPasswordInitCodeScreen, Initializable {
    private NewPasswordInitCodePresenter _presenter;

    @FXML
    private JFXTextField _txtVerifyCode;

    public NewPasswordInitCodeScreenImpl(NewPasswordInitCodePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/sign_in_help_code.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showSuccess(String message) {

    }

    @Override
    public void showLoading() {

    }

    @FXML
    public void next()
    {
        _presenter.sendCode(_txtVerifyCode.getText());
    }

    @FXML
    public void resendCode()
    {
        _presenter.resendCode();
    }

    public void navigateToInputNewPasswordScreen()
    {
        navigate(NewPasswordScreenImpl.class);
    }
}

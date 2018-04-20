package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email;

import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitEmailScreenImpl extends FXMLScreen implements NewPasswordInitEmailScreen, Initializable {
    private NewPasswordInitEmailPresenter _presenter;

    @FXML
    private JFXTextField _txtNewPasswordEmail;

    public NewPasswordInitEmailScreenImpl(NewPasswordInitEmailPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/sign_in_help.fxml";
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
        _presenter.sendEmailToResetPassword(_txtNewPasswordEmail.getText());
    }

    public void navigateToInputCode()
    {
        navigate(NewPasswordInitCodeScreenImpl.class);
    }
}

package com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code;

import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.inject.Inject;
import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class SignUpConfirmationCodeScreenImpl extends FXMLScreen implements SignUpConfirmationCodeScreen, Initializable {

    private SignUpConfirmationCodePresenter _presenter;

    @FXML
    private JFXTextField _txtVerifyCode;


    @Inject
    public SignUpConfirmationCodeScreenImpl(SignUpConfirmationCodePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
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
        return "/view/sign_in/sign_up_code.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void next()
    {
        _presenter.sendConfirmationCode(_txtVerifyCode.getText());
    }

    @FXML
    public void resendCode()
    {
        _presenter.resendCode();
    }

    @Override
    public void onNavigateToHome()
    {
        showSuccess("This is home");
    }
}

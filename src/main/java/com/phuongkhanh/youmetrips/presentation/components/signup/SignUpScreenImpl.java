package com.phuongkhanh.youmetrips.presentation.components.signup;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.SignUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class SignUpScreenImpl extends FXMLScreen implements SignUpScreen, Initializable {

    private final SignUpPresenter _presenter;

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


    public SignUpScreenImpl(final SignUpPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @FXML
    public void signUp() {
        _presenter.signUp(_txtEmailOrPhoneSignUp.getText(),
                _txtPasswordSignUp.getText(),
                _txtConfirmPasswordSignUp.getText(),
                _txtFirstNameSignUp.getText(),
                _txtLastNameSignUp.getText());

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

    public void xacnhan(){

    }

    @Override
    protected String fxmlPath() {
        return "/sign_in_help_code.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

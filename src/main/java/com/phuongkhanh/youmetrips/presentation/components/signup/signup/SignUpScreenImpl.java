package com.phuongkhanh.youmetrips.presentation.components.signup.signup;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javax.inject.Inject;
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
        return "/sign_up.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    public void navigateBackLogin()
    {
        navigateBack();
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
    public void onNavigateToSignUpReceiveCode()
    {
        navigate(SignUpConfirmationCodeScreenImpl.class);
    }


}

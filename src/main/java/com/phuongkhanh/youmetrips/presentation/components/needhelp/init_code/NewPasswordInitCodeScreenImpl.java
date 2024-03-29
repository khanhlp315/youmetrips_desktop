package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_new_password.NewPasswordScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.LoadingPane;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitCodeScreenImpl extends FXMLScreen implements NewPasswordInitCodeScreen, Initializable {
    private NewPasswordInitCodePresenter _presenter;

    @FXML
    private JFXTextField _txtVerifyCode;
    @FXML
    private LoadingPane _loadingPane;
    @FXML
    private JFXButton _btnNextHelpCode;

    public NewPasswordInitCodeScreenImpl(NewPasswordInitCodePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/view/sign_in/sign_in_help_code.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void showLoading() {
    }

    @FXML
    public void next() {
        _presenter.sendCode(_txtVerifyCode.getText());
    }


    public void navigateToInputNewPasswordScreen() {
        navigate(NewPasswordScreenImpl.class);
    }

    @Override
    public void setLoading(Boolean isLoading) {
        if (isLoading) {
            _loadingPane.setVisible(true);
            _btnNextHelpCode.setVisible(false);
        } else {
            _loadingPane.setVisible(false);
            _btnNextHelpCode.setVisible(true);
        }
    }

    @FXML
    public void requestToNavigateBack() {
        _presenter.requestToNavigateBack();
    }

    @Override
    public void onNavigateBack() {
        navigateBack();
    }
}

package com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_code.NewPasswordInitCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.LoadingPane;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * @author by LeVoGiaKhang
 */
public class NewPasswordInitEmailScreenImpl extends FXMLScreen implements NewPasswordInitEmailScreen, Initializable {
    private NewPasswordInitEmailPresenter _presenter;

    @FXML
    private JFXTextField _txtNewPasswordEmail;
    @FXML
    private LoadingPane _loadingPane;
    @FXML
    private JFXButton _btnNextHelp;

    private AnchorPane pane;

    public NewPasswordInitEmailScreenImpl(NewPasswordInitEmailPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/view/sign_in/sign_in_help.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _loadingPane.setVisible(false);
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
    public void next() {
        _presenter.sendEmailToResetPassword(_txtNewPasswordEmail.getText());
    }

    public void navigateToInputCode() {
        navigate(NewPasswordInitCodeScreenImpl.class);
    }

    @Override
    public void setLoading(Boolean isLoading) {
        if (isLoading)
        {
            _loadingPane.setVisible(true);
            _btnNextHelp.setVisible(false);
        }
        else {
            _loadingPane.setVisible(false);
            _btnNextHelp.setVisible(true);
        }
    }
}

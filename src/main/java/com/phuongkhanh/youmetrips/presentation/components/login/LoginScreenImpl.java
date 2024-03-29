package com.phuongkhanh.youmetrips.presentation.components.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.needhelp.init_email.NewPasswordInitEmailScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.confirmation_code.SignUpConfirmationCodeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.signup.signup.SignUpScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.LoadingPane;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.HomeWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.inject.Provider;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreenImpl extends FXMLScreen implements LoginScreen, Initializable {
    private final LoginPresenter _presenter;
    private final Provider<HomeWindow> _homeWindow;

    @FXML
    private JFXTextField _txtEmailOrPhoneLogin;
    @FXML
    private JFXPasswordField _txtPasswordLogin;
    @FXML
    private LoadingPane _loadingPane;
    @FXML
    private LoadingPane _loadingPaneWithFB;
    @FXML
    private JFXButton _btnLogin;
    @FXML
    private JFXButton _btnLoginWithFB;
    @FXML
    private ImageView _imgFB;


    public LoginScreenImpl(final LoginPresenter presenter, final Provider<HomeWindow> homeWindow) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.checkForAutoLogin();

        _homeWindow = homeWindow;
    }

    @Override
    public void navigateToSignup() {
        _presenter.requestToSignup();
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

    @Override
    public void setLoading(Boolean isLoading) {
        if (isLoading) {
            if (_presenter.getState() == 1) {
                _loadingPane.setVisible(true);
                _btnLogin.setVisible(false);
            } else {

                _loadingPaneWithFB.setVisible(false);
                _btnLoginWithFB.setVisible(false);
                _imgFB.setVisible(false);
            }
        } else {
            _loadingPane.setVisible(false);
            _loadingPaneWithFB.setVisible(false);
            _btnLogin.setVisible(true);
            _btnLoginWithFB.setVisible(true);
            _imgFB.setVisible(true);
        }
    }

    @Override
    protected String fxmlPath() {
        return "/view/sign_in/sign_in.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _loadingPane.setVisible(false);
        _loadingPaneWithFB.setVisible(false);
    }

    @FXML
    public void login() {
        _presenter.login(_txtEmailOrPhoneLogin.getText(), _txtPasswordLogin.getText());
    }

    @FXML
    public void loginWithFB() {
        _presenter.loginWithFB();
    }

    @FXML
    public void navigateToSignUp() {
        // click button Create in sign_in.fxml
        navigate(SignUpScreenImpl.class);
    }

    @FXML
    public void needHelp() {
        navigate(NewPasswordInitEmailScreenImpl.class);
    }

    @Override
    public void navigateToSignUpConfirmationCodeScreen() {
        navigate(SignUpConfirmationCodeScreenImpl.class);
    }

    @Override
    public void navigateToHome() {
        getWindow().close();

        HomeWindow homeWindow = _homeWindow.get();
        homeWindow.attach(new Stage());
        homeWindow.show();
    }
}

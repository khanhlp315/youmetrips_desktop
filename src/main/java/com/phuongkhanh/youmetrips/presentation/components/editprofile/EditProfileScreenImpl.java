package com.phuongkhanh.youmetrips.presentation.components.editprofile;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Country;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EditProfileScreenImpl extends FXMLScreen
        implements EditProfileScreen, Initializable {

    EditProfilePresenter _presenter;

    @FXML
    private JFXTextField _tfFirstName;

    @FXML
    private JFXTextField _tfLastName;

    @FXML
    private JFXTextField _tfJob;

    @FXML
    private JFXTextArea _taBio;

    @FXML
    private Rectangle _rectAvatar;

    public EditProfileScreenImpl(EditProfilePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchProfile();
        _presenter.fetchCountries();
    }

    @Override
    public void updateProfile(Profile profile) {
        _tfFirstName.setText(profile.getFirstName());
        _tfLastName.setText(profile.getLastName());
        _tfJob.setText(profile.getOccupation());
        _taBio.setText(profile.getBio());
    }

    @Override
    public void updateAvatarUrl(String url) {

    }

    @Override
    public void showNext() {

    }

    @Override
    public void hideNext() {

    }

    @Override
    public void setLoading(boolean value) {

    }

    @Override
    public void updateCountries(List<Country> countries) {

    }

    @Override
    protected String fxmlPath() {
        return "/view/edit_profile/edit_profile.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

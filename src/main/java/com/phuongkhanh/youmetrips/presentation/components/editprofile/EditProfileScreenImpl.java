package com.phuongkhanh.youmetrips.presentation.components.editprofile;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Country;
import com.phuongkhanh.youmetrips.services.api.models.EditedUserProfile;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    private ToggleGroup _genderToggleGroup;

    @FXML
    private RadioButton _rbMale;

    @FXML
    private RadioButton _rbFemale;

    @FXML
    private JFXDatePicker _dpBirthday;

    @FXML
    private JFXTextField _tfPhone;

    @FXML
    private JFXTextField _tfEmail;

    @FXML
    private JFXTextField _tfAddress;

    @FXML
    private JFXComboBox _cbNationality;

    private EditedUserProfile _editedUserProfile;

    public EditProfileScreenImpl(EditProfilePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchProfile();
    }

    @Override
    public void updateProfile(Profile profile) {
        _tfFirstName.setText(profile.getFirstName());
        _tfLastName.setText(profile.getLastName());
        _tfJob.setText(profile.getOccupation() == null ? "" : profile.getOccupation());
        _taBio.setText(profile.getBio() == null? "": profile.getBio());
        if(profile.getGender()!= null){
            _genderToggleGroup.selectToggle(_genderToggleGroup.getToggles().stream().filter(toggle -> toggle.getUserData().equals(profile.getGender())).findFirst().get());
        }
        if(profile.getBirthday() != null){
            _dpBirthday.setValue(LocalDate.of(profile.getBirthday().getYear(), profile.getBirthday().getMonthValue(), profile.getBirthday().getDayOfMonth()));
        }
        _tfPhone.setText(profile.getPhoneNumber() == null? "": profile.getPhoneNumber());
        _tfEmail.setText(profile.getEmail() == null? "": profile.getEmail());
        if(profile.getIdentifyingMethod().equals("BY_EMAIL")){
            _tfEmail.setEditable(false);
        }
        else if(profile.getIdentifyingMethod().equals("BY_PHONE_NUMBER")){
            _tfPhone.setEditable(true);
        }

        _editedUserProfile = new EditedUserProfile();
        _editedUserProfile.setPhoneNumber(profile.getPhoneNumber());
        _editedUserProfile.setOccupation(profile.getOccupation());
        _editedUserProfile.setLastName(profile.getLastName());
        _editedUserProfile.setGender(profile.getGender());
        _editedUserProfile.setNationality(profile.getNationality());
        _editedUserProfile.setAvatar(profile.getAvatar());
        _editedUserProfile.setAddress(profile.getAddress());
        _editedUserProfile.setBio(profile.getBio());
        _editedUserProfile.setBirthday(profile.getBirthday()== null? null: profile.getBirthday().toString());
        _editedUserProfile.setEmail(profile.getEmail());
        _editedUserProfile.setFirstName(profile.getFirstName());
        _presenter.fetchCountries();
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
        _cbNationality.setItems(FXCollections.observableArrayList(countries.stream().map(country -> country.getCode()).collect(Collectors.toList())));
        if(_editedUserProfile != null){
            _cbNationality.setValue(_editedUserProfile.getNationality());
        }
    }

    @Override
    public void close() {
        getWindow().close();
    }

    @Override
    protected String fxmlPath() {
        return "/view/edit_profile/edit_profile.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _tfPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setPhoneNumber(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _tfEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setEmail(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _tfJob.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setOccupation(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _tfLastName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setLastName(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _tfFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setFirstName(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _tfAddress.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setAddress(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _taBio.textProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setBio(newValue);
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _genderToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            _editedUserProfile.setGender(newValue.getUserData().toString());
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _dpBirthday.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(_editedUserProfile == null){
                return;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            _editedUserProfile.setBirthday(newValue.format(formatter));
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
        _cbNationality.valueProperty().addListener((observable, oldValue, newValue) -> {
            _editedUserProfile.setNationality(newValue.toString());
            _presenter.onEditedProfileUpdated(_editedUserProfile);
        });
    }

    @FXML
    public void save(){
        if(_editedUserProfile == null){
            return;
        }
        _presenter.updateProfile(_editedUserProfile);
    }
}

package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceLocationScreenImpl extends FXMLScreen
        implements TrekkingPlaceLocationScreen, Initializable {

    private TrekkingPlaceLocationPresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private JFXTextField _tfPlace;

    @Inject
    public TrekkingPlaceLocationScreenImpl(TrekkingPlaceLocationPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void showContinue() {
        _btnNext.setDisable(false);
    }

    @Override
    public void hideContinue() {
        _btnNext.setDisable(true);
    }

    @Override
    public void showMap() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void navigateToPhotos() {
        navigate(TrekkingPlacePhotosScreenImpl.class);
    }

    @FXML
    public void onNavigateToPhotos()
    {
        _presenter.requestToNavigateToPhotos(_tfPlace.getText());
    }
    @Override
    protected String fxmlPath() {
        return "/view/create_place/step2.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _tfPlace.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                _presenter.onLocationUpdated(_tfPlace.getText());
            }
        });
    }
}

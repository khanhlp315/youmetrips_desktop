package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceNameScreenImpl extends FXMLScreen
implements TrekkingPlaceNameScreen, Initializable {

    private TrekkingPlaceNamePresenter _presenter;

    @FXML
    private JFXTextField _tfPlace;

    @FXML
    private JFXButton _btnNext;

    @Inject
    public TrekkingPlaceNameScreenImpl(TrekkingPlaceNamePresenter presenter)
    {
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
    public void showError(String title, String message) {

    }

    @Override
    public void navigateToLocation() {
        CreatePlace createPlace = new CreatePlace();
        createPlace.setName(_tfPlace.getText());

        navigate(TrekkingPlaceLocationScreenImpl.class);
    }

    @FXML
    public void onNavigateToLocation()
    {
        _presenter.onInputUpdated(_tfPlace.getText());
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step1.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

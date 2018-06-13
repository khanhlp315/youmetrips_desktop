package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceLocationScreenImpl extends FXMLScreen
        implements TrekkingPlaceLocationScreen, Initializable {

    @Inject
    public TrekkingPlaceLocationScreenImpl(TrekkingPlaceLocationPresenter presenter) {

    }

    @Override
    public void showContinue() {

    }

    @Override
    public void hideContinue() {

    }

    @Override
    public void showMap() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

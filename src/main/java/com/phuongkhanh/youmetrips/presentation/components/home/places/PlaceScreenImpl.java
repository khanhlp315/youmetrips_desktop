package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceScreenImpl extends FXMLScreen
implements PlaceScreen, Initializable {
    @Override
    public void updatePlaces(List<Place> places) {

    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void onLiked(int id) {

    }

    @Override
    public void onUnliked(int id) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

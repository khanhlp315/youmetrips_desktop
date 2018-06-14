package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceDetailsScreenImpl extends FXMLScreen
        implements PlaceDetailsScreen, Initializable {

    @Inject
    public PlaceDetailsScreenImpl(PlaceDetailsPresenter presenter) {

    }

    @Override
    public void updatePlaceInfo(PlaceDetails placeDetails) {

    }

    @Override
    public void navigateToCreateTrekkingPlan() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void updateUserRating(int rate) {

    }

    @Override
    public void onLiked() {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    public void updateUserAvatar(String avatar) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

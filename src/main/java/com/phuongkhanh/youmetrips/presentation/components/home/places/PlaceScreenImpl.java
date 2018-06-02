package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceScreenImpl extends FXMLScreen
implements PlaceScreen, Initializable {

    @Inject
    public PlaceScreenImpl(PlacePresenter presenter)
    {

    }

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
    @FXML
    public void onPlanClicked()
    {

    }

    @FXML
    public void onFriendRequestClicked()
    {

    }

    @FXML
    public void onCreateTrekkingPlanClicked()
    {

    }

    @FXML
    public void onCreateTrekkingPlaceClicked()
    {

    }

    @FXML
    public void onProfileClicked()
    {

    }

    @FXML
    public void onEditProfileClicked()
    {

    }
}

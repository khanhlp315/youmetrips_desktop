package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.controls.PlaceItem;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PlaceScreenImpl extends FXMLScreen
implements PlaceScreen, Initializable {

    @FXML
    private JFXListView _lvPlaces;

    private final PlacePresenter _presenter;

    @Inject
    public PlaceScreenImpl(PlacePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchPlaces();
    }

    @Override
    public void updatePlaces(List<Place> places) {
        List<PlaceItem> placeItems = places.stream().map(
                place -> {
                    String tags = "";
                    for (String tag: place.getTags()) {
                        tags = tag + ",";
                    }
                    return new PlaceItem(
                            place.getCoverImageUrl(),
                            place.getName(),
                            tags,
                            place.getNumberOfPeopleGoing(),
                            place.getNumberOfLikes()
                    );
                }).collect(Collectors.toList());

        _lvPlaces.getItems().addAll(placeItems);
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
        return "/view/home/places/places.fxml";
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

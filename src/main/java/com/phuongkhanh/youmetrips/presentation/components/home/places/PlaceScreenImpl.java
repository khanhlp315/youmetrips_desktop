package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.PlaceCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlaceScreenImpl extends FXMLScreen
        implements PlaceScreen, Initializable {

    private final PlacePresenter _presenter;
    @FXML
    private JFXListView _lvPlaces;

    @Inject
    public PlaceScreenImpl(PlacePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public Scene render() {
        _presenter.fetchPlaces();
        return super.render();
    }

    @Override
    public void updatePlaces(List<Place> places) {
        _lvPlaces.setItems(FXCollections.observableArrayList(places));
    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    public void navigateToCreateTrekkingPlan() {
    }

    @Override
    public void navigateToPlan() {
        navigate(PlanScreenImpl.class);
    }

    @Override
    public void navigateToProfile() {
        navigate(ProfileScreenImpl.class);
    }

    @Override
    public void navigateToEditProfile() {
        navigate(EditProfileScreenImpl.class);
    }

    @Override
    public void navigateToFriendRequest() {
        navigate(FriendRequestsScreenImpl.class);
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
        _lvPlaces.setCellFactory(param -> new PlaceCell());
    }

    @FXML
    public void onPlanClicked() {
        _presenter.requestNavigateToPlan();
    }

    @FXML
    public void onFriendRequestClicked() {
        _presenter.requestNavigateToFriendRequest();
    }

    @FXML
    public void onCreateTrekkingPlanClicked() {

    }

    @FXML
    public void onCreateTrekkingPlaceClicked() {

    }

    @FXML
    public void onProfileClicked() {
        _presenter.requestNavigateToProfile();
    }

    @FXML
    public void onEditProfileClicked() {
        _presenter.requestNavigateToEditProfile();
    }
}

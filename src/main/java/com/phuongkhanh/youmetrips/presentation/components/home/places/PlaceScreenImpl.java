package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.PlaceItem;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import org.omg.CORBA.ACTIVITY_REQUIRED;

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

    @Override
    public Scene render() {
        _presenter.fetchPlaces();
        return super.render();
    }

    @Inject
    public PlaceScreenImpl(PlacePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
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

    }
    @FXML
    public void onPlanClicked()
    {
        _presenter.requestNavigateToPlan();
    }

    @FXML
    public void onFriendRequestClicked()
    {
        _presenter.requestNavigateToFriendRequest();
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
        _presenter.requestNavigateToProfile();
    }

    @FXML
    public void onEditProfileClicked()
    {
        _presenter.requestNavigateToEditProfile();
    }
}

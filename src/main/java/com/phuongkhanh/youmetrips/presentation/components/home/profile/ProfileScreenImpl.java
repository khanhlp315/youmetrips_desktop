package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.UserPlanItem;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProfileScreenImpl extends FXMLScreen
        implements ProfileScreen, Initializable {
    private final ProfilePresenter _presenter;

    @FXML
    private Label _lblFirstName;

    @FXML
    private Label _lblLastName;

    @FXML
    private Label _lblOccupation;

    @FXML
    private Label _lblNationality;

    @FXML
    private JFXListView _lvPlans;


    @Inject
    public ProfileScreenImpl(final ProfilePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchProfile();

    }

    @Override
    public void updateProfile(Profile profile) {
        _lblFirstName.setText(profile.getFirstName());
        _lblLastName.setText(profile.getLastName());
        if (profile.getOccupation() != null) {
            _lblOccupation.setText(profile.getOccupation());
        } else {
            _lblOccupation.setText("Occupation not yet updated");
        }
        if (profile.getNationality() != null) {
            _lblNationality.setText(profile.getNationality());
        } else {
            _lblNationality.setText("Nationality not yet updated");
        }

        List<UserPlanItem> userPlanItems = profile.getTrekkingPlanSet().stream().map(plan -> {
            return new UserPlanItem(
                    plan.getPlace().getCoverImageUrl(),
                    plan.getPlace().getName(),
                    plan.getWhenToGoMin().toString() + " - " + plan.getWhenToGoMax().toString(),
                    String.valueOf(plan.getHowLongMin()) + " - " + String.valueOf(plan.getHowLongMax()) + " days");
        }).collect(Collectors.toList());

        _lvPlans.getItems().addAll(userPlanItems);
    }

    @Override
    public void navigateToStart() {

    }

    @Override
    public void navigateToCreateTrekkingPlan() {
    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    public void navigateToEditProfile() {
        navigate(EditProfileScreenImpl.class);
    }

    @Override
    public void navigateToPlanList() {
        navigate(PlanScreenImpl.class);
    }

    @Override
    public void navigateToFriendList() {
        navigate(FriendRequestsScreenImpl.class);
    }

    @Override
    public void navigateToPlanDetails(int planId) {
        navigate(PlanDetailsScreenImpl.class);
    }

    @Override
    public void navigateToPlace() {
        navigate(PlaceScreenImpl.class);
    }

    @Override
    public void updateFriends(List<Friend> friends) {

    }

    @FXML
    public void onFriendRequestClicked() {
        _presenter.requestNavigateToFriendList();
    }

    @FXML
    public void onPlanClicked() {
        _presenter.requestNavigateToPlanList();
    }

    @FXML
    public void onPlaceClicked() {
        //_presenter.requestNavigateToPlaceList();
    }

    @FXML
    public void onCreateTrekkingPlanClicked() {
        _presenter.requestNavigateToCreateTrekkingPlan();
    }

    @FXML
    public void onCreateTrekkingPlaceClicked() {
        //  _presenter.requestNavigateToCreateTrekkingPlace();
    }

    @FXML
    public void onEditProfileClicked() {
        // _presenter.requestNavigateToEditProfile();
    }

    @FXML
    public void onPlaceDetailsClicked() {

    }

    @Override
    protected String fxmlPath() {
        return "/view/home/profile/profile.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

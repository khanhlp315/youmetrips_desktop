package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.FriendItem;
import com.phuongkhanh.youmetrips.presentation.controls.HomePane;
import com.phuongkhanh.youmetrips.presentation.controls.LoadingPane;
import com.phuongkhanh.youmetrips.presentation.controls.UserPlanItem;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

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

    @FXML
    private JFXListView _lvFriends;

    @FXML
    private Label _lblFriendCount;

    @FXML
    private LoadingPane _loadingPane;

    @FXML
    private VBox _profileBox;

    @FXML
    private HomePane _homePane;

    @FXML
    private Rectangle _rectAvatar;

    @Inject
    public ProfileScreenImpl(final ProfilePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);

    }

    @Override
    public void updateProfile(Profile profile) {
        Image image = new Image(profile.getAvatar() == null ? CommonUtils.getNeutralAvatar() : profile.getAvatar());
        _rectAvatar.setFill(new ImagePattern(image));
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
    public void navigateToPlaceList() {
        navigate(PlaceScreenImpl.class);
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
    public void setLoading(boolean isLoading) {
        _homePane.setHomeNode(isLoading? _loadingPane: _profileBox);
    }

    @Override
    public Scene render() {
        _presenter.fetchProfile();
        _presenter.fetchFriends();
        return super.render();
    }

    @Override
    public void updateFriends(List<Friend> friends) {
        _lblFriendCount.setText(String.valueOf(friends.size()));
        List<FriendItem> friendItems = friends.stream().map(
                friend -> {
                    return new FriendItem(friend.getUserAvatarUrl(), friend.getUserFirstName(), friend.getUserLastName());
                }).collect(Collectors.toList());

        _lvFriends.getItems().addAll(friendItems);
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
        _presenter.requestNavigateToPlaceList();
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

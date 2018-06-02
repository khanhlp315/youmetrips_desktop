package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileScreenImpl extends FXMLScreen
        implements ProfileScreen, Initializable
{
    private final ProfilePresenter _presenter;

    @Inject
    public ProfileScreenImpl(final ProfilePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);

    }

    @Override
    public void updateProfile(Profile profile) {

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
    public void onFriendRequestClicked(){
        _presenter.requestNavigateToFriendList();
    }

    @FXML
    public void onPlanClicked()
    {
        _presenter.requestNavigateToPlanList();
    }

    @FXML
    public void onPlaceClicked()
    {
        //_presenter.requestNavigateToPlaceList();
    }

    @FXML
    public void onCreateTrekkingPlanClicked()
    {
        _presenter.requestNavigateToCreateTrekkingPlan();
    }

    @FXML
    public void onCreateTrekkingPlaceClicked()
    {
      //  _presenter.requestNavigateToCreateTrekkingPlace();
    }

    @FXML
    public void onEditProfileClicked()
    {
       // _presenter.requestNavigateToEditProfile();
    }

    @FXML
    public void onPlaceDetailsClicked()
    {

    }
    
    @Override
    protected String fxmlPath() {
        return "/view/home/profile/profile.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.RelevantPlanCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlanScreenImpl extends FXMLScreen
        implements PlanScreen, Initializable {

    private final PlanPresenter _presenter;

    @FXML
    private JFXListView _lvPlans;

    @Inject
    public PlanScreenImpl(PlanPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public Scene render() {
        _presenter.fetchPlans();
        return super.render();
    }

    @Override
    public void updatePlans(List<RelevantPlan> invidualPlans) {
        _lvPlans.setItems(FXCollections.observableArrayList(invidualPlans));
    }

    @Override
    public void navigateToCreateTrekkingPlan() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void showAddFriendPopup(int userId) {

    }

    @Override
    public void navigateToPlanDetails(int planId) {

    }

    @Override
    public void navigateToPlace() {
        navigate(PlaceScreenImpl.class);
    }

    @Override
    public void navigateToEditProfile() {
        navigate(EditProfileScreenImpl.class);
    }

    @Override
    public void navigateToProfile() {
        navigate(ProfileScreenImpl.class);
    }

    @Override
    public void navigateToFriendRequest() {
        navigate(FriendRequestsScreenImpl.class);
    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    protected String fxmlPath() {
        return "/view/home/plans/plans.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _lvPlans.setCellFactory(param -> new RelevantPlanCell());
    }

    @FXML
    public void onPlaceClicked() {
        _presenter.requestNavigateToPlace();
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

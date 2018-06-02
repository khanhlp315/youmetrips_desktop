package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.PlanItem;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PlanScreenImpl extends FXMLScreen
implements PlanScreen, Initializable {

    private final PlanPresenter _presenter;

    @FXML
    private JFXListView _lvPlans;

    @Override
    public Scene render() {
        _presenter.fetchPlans();
        return super.render();
    }

    @Inject
    public PlanScreenImpl(PlanPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void updatePlans(List<RelevantPlan> invidualPlans) {
        List<PlanItem> planItems = invidualPlans.stream().map(plan -> {
            return new PlanItem(
                    plan.getUserFirstName(),
                    plan.getUserLastName(),
                    plan.getUserAvatarUrl(),
                    plan.getUserOccupation(),
                    plan.getUserNationalityFlagUrl(),
                    plan.getPlace().getName(),
                    plan.getHotelLevel(),
                    "",
                    "",
                    plan.getNumberOfComments()
            );
        }).collect(Collectors.toList());

        _lvPlans.getItems().addAll(planItems);
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

    }

    @FXML
    public void onPlaceClicked()
    {
        _presenter.requestNavigateToPlace();
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

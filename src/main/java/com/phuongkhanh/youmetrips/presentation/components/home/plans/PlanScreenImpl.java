package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.RelevantPlanCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlaceWindow;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.presentation.windows.EditProfileWindow;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PlanScreenImpl extends FXMLScreen
implements PlanScreen, Initializable {

    private final PlanPresenter _presenter;
    private final Provider<CreatePlanWindow> _planWindow;
    private final Provider<CreatePlaceWindow> _placeWindow;
    private final Provider<EditProfileWindow> _editProfileWindow;

    @FXML
    private ListView _lvPlans;

    @Override
    public Scene render() {
        _presenter.fetchPlans();
        return super.render();
    }

    @Inject
    public PlanScreenImpl(PlanPresenter presenter, Provider<CreatePlanWindow> planWindow, Provider<CreatePlaceWindow> placeWindow, Provider<EditProfileWindow> editProfileWindow)
    {
        _presenter = presenter;
        _presenter.setView(this);
        _planWindow = planWindow;
        _placeWindow = placeWindow;
        _editProfileWindow = editProfileWindow;
    }

    @Override
    public void updatePlans(List<RelevantPlan> invidualPlans) {
        _lvPlans.setItems(FXCollections.observableArrayList(invidualPlans.stream().map(RelevantPlanCell::new).collect(Collectors.toList())));
    }

    @Override
    public void navigateToCreateTrekkingPlan() {
        CreatePlanWindow createPlanWindow = _planWindow.get();
        createPlanWindow.attach(new Stage());
        createPlanWindow.showAndWait();
        refresh();
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
        EditProfileWindow editProfileWindow = _editProfileWindow.get();
        editProfileWindow.attach(new Stage());
        editProfileWindow.showAndWait();
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
        CreatePlaceWindow createPlaceWindow = _placeWindow.get();
        createPlaceWindow.attach(new Stage());
        createPlaceWindow.show();
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
        _presenter.requestNavigateToCreateTrekkingPlan();
    }

    @FXML
    public void onCreateTrekkingPlaceClicked()
    {
        _presenter.requestNavigateToCreateTrekkingPlace();
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

    @FXML
    public void refresh(){
        _presenter.refreshPlans();
    }
}

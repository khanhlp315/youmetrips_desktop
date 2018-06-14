package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.FriendRequestCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlaceWindow;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.presentation.windows.EditProfileWindow;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
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

public class FriendRequestsScreenImpl extends FXMLScreen
        implements FriendRequestsScreen, Initializable {

    @FXML
    private ListView _lvFriendRequests;
    private final FriendRequestsPresenter _presenter;
    private final Provider<CreatePlanWindow> _planWindow;
    private final Provider<CreatePlaceWindow> _placeWindow;
    private final Provider<EditProfileWindow> _editProfileWindow;

    @Override
    public Scene render() {
        _presenter.fetchFriendRequests();
        return super.render();
    }

    @Inject
    public FriendRequestsScreenImpl(FriendRequestsPresenter presenter, Provider<CreatePlanWindow> planWindow, Provider<CreatePlaceWindow> placeWindow, Provider<EditProfileWindow> editProfileWindow) {
        _presenter = presenter;
        _presenter.setView(this);
        _planWindow = planWindow;
        _placeWindow = placeWindow;
        _editProfileWindow = editProfileWindow;
    }

    @Override
    public void updateRequests(List<FriendRequest> requests) {
        _lvFriendRequests.setItems(FXCollections.observableArrayList(requests.stream().map(request ->
                new FriendRequestCell(request,
                        event -> _presenter.acceptRequest(request.getUserId()),
                        event -> _presenter.declineRequest(request.getUserId()))
        ).collect(Collectors.toList())));
    }

    @Override
    public void showError(String title, String message) {

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
    public void navigateToCreateTrekkingPlan() {
        CreatePlanWindow createPlanWindow = _planWindow.get();
        createPlanWindow.attach(new Stage());
        createPlanWindow.show();
    }

    @Override
    public void navigateToCreateTrekkingPlace() {
        CreatePlaceWindow createPlaceWindow = _placeWindow.get();
        createPlaceWindow.attach(new Stage());
        createPlaceWindow.show();
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
    protected String fxmlPath() {
        return "/view/home/friend_requests/friend_requests.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onPlaceClicked() {
        _presenter.requestNavigateToPlace();
    }

    @FXML
    public void onPlanClicked() {
        _presenter.requestNavigateToPlan();
    }

    @FXML
    public void onCreateTrekkingPlanClicked() {
        _presenter.requestNavigateToCreateTrekkingPlan();
    }

    @FXML
    public void onCreateTrekkingPlaceClicked() {
        _presenter.requestNavigateToCreateTrekkingPlace();
    }

    @FXML
    public void onProfileClicked() {
        _presenter.requestNavigateToProfile();
    }

    @FXML
    public void onEditProfileClicked() {
        _presenter.requestNavigateToEditProfile();
    }

    @FXML
    public void refresh() {
        _presenter.refreshRequests();
    }
}

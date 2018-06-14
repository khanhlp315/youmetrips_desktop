package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.jfoenix.controls.JFXListView;
import com.phuongkhanh.youmetrips.presentation.components.editprofile.EditProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreenImpl;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreenImpl;
import com.phuongkhanh.youmetrips.presentation.controls.PlaceCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlaceWindow;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.presentation.windows.PlaceDetailsWindow;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import dagger.Provides;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.omg.CORBA.ACTIVITY_REQUIRED;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PlaceScreenImpl extends FXMLScreen
        implements PlaceScreen, Initializable {

    @FXML
    private ListView _lvPlaces;

    private final PlacePresenter _presenter;
    private final Provider<CreatePlanWindow> _planWindow;
    private final Provider<CreatePlaceWindow> _placeWindow;
    private final Provider<PlaceDetailsWindow> _placeDetailsWindow;

    @Override
    public Scene render() {
        _presenter.fetchPlaces();
        return super.render();
    }

    @Inject
    public PlaceScreenImpl(PlacePresenter presenter, Provider<CreatePlanWindow> planWindow, Provider<CreatePlaceWindow> placeWindow, Provider<PlaceDetailsWindow> placeDetailsWindowProvider) {
        _presenter = presenter;
        _presenter.setView(this);
        _planWindow = planWindow;
        _placeWindow = placeWindow;
        _placeDetailsWindow = placeDetailsWindowProvider;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updatePlaces(List<Place> places) {
        final List<Place> places1 = places.stream().limit(5).collect(Collectors.toList());
        _lvPlaces.setItems(FXCollections.observableArrayList(places1.stream().map(PlaceCell::new).collect(Collectors.toList())));
        _lvPlaces.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                _presenter.savePlaceDetailsId(places1.get(_lvPlaces.getSelectionModel().getSelectedIndex()).getId());
                _presenter.requestNavigateToPlaceDetails();
            }
        });
    }

    @Override
    public void navigateToCreateTrekkingPlace() {
        CreatePlaceWindow createPlaceWindow = _placeWindow.get();
        createPlaceWindow.attach(new Stage());
        createPlaceWindow.showAndWait();
        refresh();
    }

    @Override
    public void navigateToCreateTrekkingPlan() {
        CreatePlanWindow createPlanWindow = _planWindow.get();
        createPlanWindow.attach(new Stage());
        createPlanWindow.show();
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
    public void navigateToPlaceDetails() {
        PlaceDetailsWindow placeDetailsWindow = _placeDetailsWindow.get();
        placeDetailsWindow.attach(new Stage());
        placeDetailsWindow.show();

    }

    @Override
    protected String fxmlPath() {
        return "/view/home/places/places.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        _presenter.refreshPlaces();
    }
}

package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FriendRequestsScreenImpl extends FXMLScreen
        implements FriendRequestsScreen, Initializable {

    @Inject
    public FriendRequestsScreenImpl(FriendRequestsPresenter presenter) {

    }

    @Override
    public void updateRequests(List<FriendRequest> requests) {

    }

    @Override
    public void removeRequest(int id) {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onPlaceClicked() {

    }

    @FXML
    public void onPlanClicked() {

    }

    @FXML
    public void onCreateTrekkingPlanClicked() {

    }

    @FXML
    public void onCreateTrekkingPlaceClicked() {

    }

    @FXML
    public void onProfileClicked() {

    }

    @FXML
    public void onEditProfileClicked() {

    }
}

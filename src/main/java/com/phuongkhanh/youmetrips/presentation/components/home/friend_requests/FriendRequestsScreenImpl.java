package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FriendRequestsScreenImpl extends FXMLScreen
implements FriendRequestsScreen, Initializable {
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
}

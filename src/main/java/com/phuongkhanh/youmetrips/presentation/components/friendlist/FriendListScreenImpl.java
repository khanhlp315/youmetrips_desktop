package com.phuongkhanh.youmetrips.presentation.components.friendlist;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FriendListScreenImpl extends FXMLScreen
        implements FriendListScreen, Initializable {

    public FriendListScreenImpl(FriendListPresenter presenter) {

    }

    @Override
    public void updateFriends(List<Friend> friends) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

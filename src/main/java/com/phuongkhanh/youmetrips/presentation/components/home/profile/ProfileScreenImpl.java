package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileScreenImpl extends FXMLScreen
        implements ProfileScreen, Initializable
{
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
    public void navigateToEditProfile() {

    }

    @Override
    public void navigateToPlanList() {

    }

    @Override
    public void navigateToFriendList() {

    }

    @Override
    public void navigateToPlanDetails(int planId) {

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

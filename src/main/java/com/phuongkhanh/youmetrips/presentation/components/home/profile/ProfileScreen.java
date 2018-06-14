package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.api.models.Profile;

import java.util.List;

public interface ProfileScreen extends JFXScreen {
    void updateProfile(Profile profile);

    void navigateToStart();

    void navigateToCreateTrekkingPlan();

    void navigateToCreateTrekkingPlace();

    void navigateToEditProfile();

    void navigateToPlanList();

    void navigateToPlaceList();

    void navigateToFriendList();

    void navigateToPlanDetails(int planId);

    void setLoading(boolean isLoading);

    void updateFriends(List<Friend> friends);

}

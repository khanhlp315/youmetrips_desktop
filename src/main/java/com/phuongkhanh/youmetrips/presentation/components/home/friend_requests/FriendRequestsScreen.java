package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;

import java.util.List;

public interface FriendRequestsScreen extends JFXScreen {
    void updateRequests(List<FriendRequest> requests);

    void showError(String title, String message);

    void navigateToPlan();

    void navigateToProfile();

    void navigateToEditProfile();

    void navigateToCreateTrekkingPlan();

    void navigateToCreateTrekkingPlace();

    void navigateToPlace();
}

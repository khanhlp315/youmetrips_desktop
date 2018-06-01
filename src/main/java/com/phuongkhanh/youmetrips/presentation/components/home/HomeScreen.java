package com.phuongkhanh.youmetrips.presentation.components.home;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface HomeScreen extends JFXScreen {
    void navigateToProfile();
    void navigateToFriendRequests();
    void navigateToPlans();
    void navigateToPlaces();

    void showError(String title, String message);
}

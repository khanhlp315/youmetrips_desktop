package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;

import java.util.List;

public interface PlaceScreen extends JFXScreen {
    void updatePlaces(List<Place> places);
    void navigateToCreateTrekkingPlace();
    void navigateToCreateTrekkingPlan();
    void navigateToPlan();
    void navigateToProfile();
    void navigateToEditProfile();
    void navigateToFriendRequest();

    void showError(String title, String message);

    void onLiked(int id);
    void onUnliked(int id);
}

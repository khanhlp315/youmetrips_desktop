package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;

public interface PlaceDetailsScreen extends JFXScreen {
    void updatePlaceInfo(PlaceDetails placeDetails);

    void navigateToCreateTrekkingPlan();

    void navigateBack();

    void showError(String title, String message);

    void updateUserRating(int rate);

    // void popupDialog(String title, String message, TextEditingController controller);

    void onLiked();

    void updateMapUrl(String mapUrl);

    void updateUserAvatar(String avatar);

    void navigateToCreatePlan();
}

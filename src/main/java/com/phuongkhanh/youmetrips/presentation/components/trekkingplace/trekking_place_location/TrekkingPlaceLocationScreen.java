package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlaceLocationScreen extends JFXScreen {
    void showContinue();

    void hideContinue();

    void showMap();

    void showError(String title, String message);

    void navigateToPhotos();
}
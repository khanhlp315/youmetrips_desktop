package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlaceNameScreen extends JFXScreen {
    void showContinue();

    void hideContinue();

    void showError(String title, String message);
    void navigateToLocation();
}

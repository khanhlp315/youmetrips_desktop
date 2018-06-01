package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

import java.io.File;

public interface TrekkingPlacePhotosScreen extends JFXScreen {
    void showContinue();
    void hideContinue();
    void showError(String title, String message);
    void addImage(File image);

    void removeImage(File image);

    void changeCoverImage(File image);
    void removeCoverImage();
}

package com.phuongkhanh.youmetrips.presentation.components.trekkingplace;

public interface TrekkingPlaceScreen {
    void navigateBack();
    void returnPlace(int placeId);
    void showError(String title, String message);
    void setLoading(boolean value);
}

package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlaceHashtagsScreen extends JFXScreen {
    void showContinue();
    void hideContinue();
    void showError(String title, String message);
    void returnPlace(int placeId);
    void setLoading(boolean value);
    void addHashtag(String text);
    void removeHashtag(String hashtag);
}

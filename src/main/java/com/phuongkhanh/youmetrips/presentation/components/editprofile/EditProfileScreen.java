package com.phuongkhanh.youmetrips.presentation.components.editprofile;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Country;
import com.phuongkhanh.youmetrips.services.api.models.Profile;

import java.util.List;

public interface EditProfileScreen extends JFXScreen {
    void updateProfile(Profile profile);

    void updateAvatarUrl(String url);

    void showNext();

    void hideNext();

    void setLoading(boolean value);

    void navigateBack();

    void updateCountries(List<Country> countries);
}

package com.phuongkhanh.youmetrips.presentation.components.editprofile;

import com.phuongkhanh.youmetrips.services.api.models.Country;
import com.phuongkhanh.youmetrips.services.api.models.EditedUserProfile;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.io.File;
import java.util.List;

public interface EditProfileService {
    void updateUserProfile(EditedUserProfile profile, int userId, String jwt);

    String uploadFile(int userId, String jwt, File file);

    List<Country> getAllCountries();

    AuthenticationStore getAuthenticationStore();

    Profile getUserProfile(int userId, String jwt);

    HomeStore getHomeStore();
}

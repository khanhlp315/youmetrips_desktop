package com.phuongkhanh.youmetrips.presentation.components.trekkingplace;

import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

import java.io.File;

public interface TrekkingPlaceService {
    int createPlace(int userId, String jwt, CreatePlace place);

    String uploadFile(int userId, String jwt, File file);

    AuthenticationStore getAuthenticationStore();
}

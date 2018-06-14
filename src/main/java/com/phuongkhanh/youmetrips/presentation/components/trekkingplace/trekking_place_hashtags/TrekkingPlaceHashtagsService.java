package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.io.File;

public interface TrekkingPlaceHashtagsService {
    HomeStore getHomeStore();

    int createPlace(int userId, String jwt, CreatePlace place);

    String uploadFile(int userId, String jwt, File file);

    AuthenticationStore getAuthenticationStore();

}

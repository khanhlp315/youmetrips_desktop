package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.io.File;
import java.util.List;

public interface TrekkingPlacePhotosService {
    String uploadFile(int userId, String jwt, File file);
    HomeStore getHomeStore();
    AuthenticationStore getAuthenticationStore();
}

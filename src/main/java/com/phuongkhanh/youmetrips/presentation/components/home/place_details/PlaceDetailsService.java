package com.phuongkhanh.youmetrips.presentation.components.home.place_details;

import com.phuongkhanh.youmetrips.services.api.models.CreateTag;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

public interface PlaceDetailsService {
    PlaceDetails getPlaceDetails(int userId, int placeId, String jwt);

    void review(int rate, String message, int userId, int placeId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();

    void like(int userId, int placeId, String jwt);

    String getMapUrl(String location);

    void addTag(CreateTag tag, int userId, int placeId, String jwt);

    Profile getUserProfile(int userId, String jwt);
}

package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.services.api.models.Place;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface PlaceService {
    List<Place> fetchPlaces(int userId, String jwt);

    void like(int userId, int placeId, String jwt);

    void unlike(int userId, int placeId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();
}

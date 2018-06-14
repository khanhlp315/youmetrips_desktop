package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.phuongkhanh.youmetrips.services.api.models.Place;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface TrekkingPlanPlaceService {
    List<Place> fetchPlaces(int userId, String jwt);

    AuthenticationStore getAuthenticationStore();

    PlaceDetails getPlaceDetails(int userId, int placeId, String jwt);

    HomeStore getHomeStore();

    String getMapUrl(String location);
}

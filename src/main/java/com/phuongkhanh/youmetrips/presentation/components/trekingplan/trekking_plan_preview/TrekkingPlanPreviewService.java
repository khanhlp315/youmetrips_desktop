package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

public interface TrekkingPlanPreviewService {
    PlaceDetails getPlaceDetails(int userId, int placeId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();

    String getMapUrl(String location);
}

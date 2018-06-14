package com.phuongkhanh.youmetrips.presentation.components.plandetails;

import com.phuongkhanh.youmetrips.services.api.models.Comment;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface PlanDetailsService {
    PlaceDetails getPlaceDetails(int userId, int placeId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();

    String getMapUrl(String location);

    List<Comment> fetchComments(int userId, int trekkingPlanId, String jwt);

    void postComment(String comment, int userId, int trekkingPlanId, String jwt);

    Profile getUserProfile(int userId, String jwt);

}

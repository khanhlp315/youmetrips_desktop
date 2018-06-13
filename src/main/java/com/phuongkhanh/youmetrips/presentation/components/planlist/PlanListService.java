package com.phuongkhanh.youmetrips.presentation.components.planlist;

import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

public interface PlanListService {
    Profile getUserProfile(int userId, String jwt);

    AuthenticationStore getAuthenticationStore();

    HomeStore getHomeStore();
}

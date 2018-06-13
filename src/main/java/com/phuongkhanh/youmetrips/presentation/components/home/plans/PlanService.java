package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import java.util.List;

public interface PlanService {
    List<RelevantPlan> fetchPlans(int userId, String jwt);

    void sendFriendRequest(int toUserId, int userId, String jwt);

    AuthenticationStore getAuthenticationStore();
    HomeStore getHomeStore();
}

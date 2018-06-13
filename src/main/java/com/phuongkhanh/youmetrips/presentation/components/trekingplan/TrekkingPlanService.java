package com.phuongkhanh.youmetrips.presentation.components.trekingplan;

import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;

public interface TrekkingPlanService {
    int createTrekkingPlan(int userId, String jwt, CreatePlan trekkingPlan);

    AuthenticationStore getAuthenticationStore();
}

package com.phuongkhanh.youmetrips.presentation.components.planlist;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.UserTrekkingPlan;

import java.util.List;

public interface PlanListScreen extends JFXScreen {
    void updatePlans(List<UserTrekkingPlan> plans);

    void navigateBack();

    void navigateToPlanDetails(int planId);
}

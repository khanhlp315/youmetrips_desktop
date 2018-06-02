package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;

import java.util.List;

public interface PlanScreen extends JFXScreen {
    void updatePlans(List<RelevantPlan> invidualPlans);

    void navigateToCreateTrekkingPlan();

    void showError(String title, String message);

    void showAddFriendPopup(int userId);

    void navigateToPlanDetails(int planId);

    void navigateToPlace();
    void navigateToEditProfile();
    void navigateToProfile();
    void navigateToFriendRequest();
    void navigateToCreateTrekkingPlace();
}

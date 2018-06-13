package com.phuongkhanh.youmetrips.presentation.components.trekingplan;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlanScreen extends JFXScreen {
    void navigateBack();

    void showError(String title, String message);

    void setLoading(boolean value);

    void returnPlan(int planId);
}

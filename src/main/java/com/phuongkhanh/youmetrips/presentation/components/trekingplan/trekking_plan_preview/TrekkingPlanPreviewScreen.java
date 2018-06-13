package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;

public interface TrekkingPlanPreviewScreen extends JFXScreen {
    void setLoading(boolean value);

    void updateMapUrl(String mapUrl);

    void updatePlan(CreatePlan plan);

    void navigateBack();

    void showError(String title, String message);

    void closeWindow();

    void updatePlaceImage(String coverImageUrl);
}

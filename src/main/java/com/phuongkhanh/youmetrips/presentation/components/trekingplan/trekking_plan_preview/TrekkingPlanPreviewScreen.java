package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlanPreviewScreen extends JFXScreen {
    void setLoading(boolean value);

    void updateMapUrl(String mapUrl);
}

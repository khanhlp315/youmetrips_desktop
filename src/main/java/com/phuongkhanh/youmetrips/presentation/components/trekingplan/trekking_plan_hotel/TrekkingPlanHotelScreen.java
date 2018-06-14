package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlanHotelScreen extends JFXScreen {
    void setCanNext(boolean value);

    void setLoading(boolean value);

    void navigateToPreview();
}

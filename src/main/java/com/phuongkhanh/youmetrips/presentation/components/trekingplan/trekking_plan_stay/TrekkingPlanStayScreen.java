package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;

public interface TrekkingPlanStayScreen extends JFXScreen {
    void setCanNext(boolean value);

    void navigateToHotel();
}

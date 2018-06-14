package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.plandetails.PlanDetailsScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class PlanDetailsWindow extends JFXWindowBase {
    @Inject
    public PlanDetailsWindow(PlanDetailsScreen planDetailsScreen){
        super(planDetailsScreen);
    }
}

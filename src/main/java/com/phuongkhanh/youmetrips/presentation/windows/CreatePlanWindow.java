package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class CreatePlanWindow extends JFXWindowBase {

    @Inject
    public CreatePlanWindow(
            final TrekkingPlanPlaceScreen placeScreen,
            final TrekkingPlanTimeScreen timeScreen,
            final TrekkingPlanStayScreen stayScreen,
            final TrekkingPlanHotelScreen hotelScreen,
            final TrekkingPlanPreviewScreen previewScreen
    ) {
        super(placeScreen, timeScreen, stayScreen, hotelScreen, previewScreen);
    }
}

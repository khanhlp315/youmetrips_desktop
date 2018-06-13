package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

public class TrekkingPlanHotelPresenter extends PresenterBase<TrekkingPlanHotelScreen> {
    void onStarsUpdated(int stars) {
        assert (getView() != null);
        if (_isValidInput(stars)) {
            getView().setCanNext(true);
        } else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(int stars) {
        return stars != 0;
    }

}

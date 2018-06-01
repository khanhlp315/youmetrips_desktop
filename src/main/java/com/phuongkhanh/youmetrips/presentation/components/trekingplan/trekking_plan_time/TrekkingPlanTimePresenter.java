package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import org.joda.time.DateTime;

public class TrekkingPlanTimePresenter extends PresenterBase<TrekkingPlanTimeScreen> {
    
    public void onInputUpdated(DateTime start, DateTime end){
        assert(getView() != null);
        if (_isValidInput(start, end)){
            getView().setCanNext(true);
        }
        else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(DateTime start, DateTime end) {
        if(start == null || end == null){
            return false;
        }

        if(end.isBefore(start)){
            return false;
        }

        return true;
    }

}

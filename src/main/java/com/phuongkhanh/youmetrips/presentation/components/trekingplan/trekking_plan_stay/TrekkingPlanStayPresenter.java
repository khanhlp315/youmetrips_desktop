package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import org.joda.time.DateTime;

public class TrekkingPlanStayPresenter extends PresenterBase<TrekkingPlanStayScreen> {

    public void onInputUpdated(int from, int to){
        assert(getView() != null);
        if (_isValidInput(from, to)){
            getView().setCanNext(true);
        }
        else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(int from, int to) {
        if(from == 0 || to == 0){
            return false;
        }

        if(from >= to){
            return false;
        }

        return true;
    }
}

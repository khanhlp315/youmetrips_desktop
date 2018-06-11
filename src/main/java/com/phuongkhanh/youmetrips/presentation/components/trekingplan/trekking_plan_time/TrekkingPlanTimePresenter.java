package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;

public class TrekkingPlanTimePresenter extends PresenterBase<TrekkingPlanTimeScreen> {

    @Inject
    public TrekkingPlanTimePresenter()
    {

    }

    public void onInputUpdated(String start, String end){
        assert(getView() != null);
        if (_isValidInput(start, end)){
            getView().setCanNext(true);
        }
        else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(String start, String end) {
        if(start == null || end == null){
            return false;
        }

//        if(end.isBefore(start)){
//            return false;
//        }

        return true;
    }

}

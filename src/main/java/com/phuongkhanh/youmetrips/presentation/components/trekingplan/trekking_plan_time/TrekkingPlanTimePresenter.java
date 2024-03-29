package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import javax.inject.Inject;
import java.time.LocalDate;

public class TrekkingPlanTimePresenter extends PresenterBase<TrekkingPlanTimeScreen> {

    private final TrekkingPlanTimeService _service;

    @Inject
    public TrekkingPlanTimePresenter(TrekkingPlanTimeService service)
    {
        _service = service;
    }

    public void onInputUpdated(LocalDate start, LocalDate end){
        assert(getView() != null);
        if (_isValidInput(start, end)){
            getView().setCanNext(true);
        }
        else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(LocalDate start, LocalDate end) {
        if(start == null || end == null){
            return false;
        }

        return !end.isBefore(start);
    }

    public void requestToNavigateToStay(String from, String to)
    {
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();
        CreatePlan plan = homeStore.getCreatePlan();
        plan.setWhenToGoMin(from);
        plan.setWhenToGoMax(to);
        getView().navigateToStay();
    }
}

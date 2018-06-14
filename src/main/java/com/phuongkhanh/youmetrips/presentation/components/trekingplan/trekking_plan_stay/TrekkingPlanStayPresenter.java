package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import javax.inject.Inject;

public class TrekkingPlanStayPresenter extends PresenterBase<TrekkingPlanStayScreen> {

    private final TrekkingPlanStayService _service;

    @Inject
    public TrekkingPlanStayPresenter(TrekkingPlanStayService service) {
        _service = service;
    }

    public void onInputUpdated(int from, int to) {
        assert (getView() != null);
        if (_isValidInput(from, to)) {
            getView().setCanNext(true);
        } else {
            getView().setCanNext(false);
        }
    }

    private boolean _isValidInput(int from, int to) {
        if (from == 0 || to == 0) {
            return false;
        }

        return from < to;
    }

    public void requestToNavigateToHotel() {
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();
        CreatePlan plan = homeStore.getCreatePlan();
        homeStore.storeCreatePlan(plan);
        getView().navigateToHotel();
    }
}

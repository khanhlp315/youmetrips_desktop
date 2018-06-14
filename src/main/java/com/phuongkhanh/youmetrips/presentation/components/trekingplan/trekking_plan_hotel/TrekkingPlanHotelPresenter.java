package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

import javax.inject.Inject;

public class TrekkingPlanHotelPresenter extends PresenterBase<TrekkingPlanHotelScreen> {

    private final TrekkingPlanHotelService _service;

    @Inject
    public TrekkingPlanHotelPresenter(TrekkingPlanHotelService service) {
        _service = service;
    }

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

    public void requestToNavigateToPreview() {
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();
        CreatePlan plan = homeStore.getCreatePlan();
        homeStore.storeCreatePlan(plan);
        getView().navigateToPreview();
    }
}

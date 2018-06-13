package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;

public class TrekkingPlanPreviewPresenter extends PresenterBase<TrekkingPlanPreviewScreen> {

    private final TrekkingPlanPreviewService _service;
    
    @Inject
    public TrekkingPlanPreviewPresenter(TrekkingPlanPreviewService service)
    {
        _service = service;
    }
    
    public void fetchLocation(int placeId) {
        HomeStore homeStore = _service.getHomeStore();

        PlaceDetails placeDetails = homeStore.getPlaceDetails(placeId);

        if(placeDetails != null){
            getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
            return;
        }

        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return _doGetLocation(placeId);
            }

            @Override
            protected void failed() {
                _onGetLocationFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            String location = task.getValue();
            _onGetLocationSucceeded(location);
        });
        new Thread(task).start();
    }

    private String _doGetLocation(int placeId)  {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        return _service.getPlaceDetails(authenticationStore.getUserId(), placeId, authenticationStore.getJwt()).getLocation();

    }

    private void _onGetLocationSucceeded(String location) {
        getView().updateMapUrl(_service.getMapUrl(location));
    }

    private void _onGetLocationFailed(Throwable throwable) {
    }
}

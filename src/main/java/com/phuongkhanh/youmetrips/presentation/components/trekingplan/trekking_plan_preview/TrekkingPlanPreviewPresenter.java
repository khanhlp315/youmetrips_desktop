package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.presentation.exceptions.ExistedUserCreatePlanToPlaceException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiException;
import com.phuongkhanh.youmetrips.services.api.exceptions.ExistedUserTrekkingPlanToPlaceException;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
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
            getView().updatePlaceImage(placeDetails.getCoverImageUrl());
            getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
            return;
        }

        Task<PlaceDetails> task = new Task<PlaceDetails>() {
            @Override
            protected PlaceDetails call() throws Exception {
                return _doGetLocation(placeId);
            }

            @Override
            protected void failed() {
                _onGetLocationFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onGetLocationSucceeded(task.getValue());
        });
        new Thread(task).start();
    }

    private PlaceDetails _doGetLocation(int placeId)  {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        return _service.getPlaceDetails(authenticationStore.getUserId(), placeId, authenticationStore.getJwt());

    }

    private void _onGetLocationSucceeded(PlaceDetails location) {
        System.out.println(location);
        getView().updatePlaceImage(location.getCoverImageUrl());
        getView().updateMapUrl(_service.getMapUrl(location.getLocation()));
    }

    private void _onGetLocationFailed(Throwable throwable) {
    }

    public void fetchPlan()
    {
        HomeStore homeStore = _service.getHomeStore();
        CreatePlan plan = homeStore.getCreatePlan();

        getView().updatePlan(plan);
        fetchLocation(plan.getPlaceId());
    }

    public void createCreatePlan(String description) {
        assert (getView() != null);
        getView().setLoading(true);

        CreatePlan plan = _service.getHomeStore().getCreatePlan();
        plan.setDescription(description);

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return _doCreateCreatePlan(plan);
            }

            @Override
            protected void failed() {
                _onCreateCreatePlanFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            Integer id = task.getValue();
            _onCreateCreatePlanSucceeded(id);
        });
        new Thread(task).start();
    }

    private int _doCreateCreatePlan(CreatePlan plan) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();

        return _service.createTrekkingPlan(userId, jwt, plan);
    }

    private void _onCreateCreatePlanFailed(Throwable throwable) {
        getView().setLoading(false);

        if (throwable instanceof ApiException) {
            getView().showError("Could not create trekking plan", "Could not connect to server, please check your connection");
        } else if (throwable instanceof ExistedUserTrekkingPlanToPlaceException) {
            getView().showError("Could not create trekking plan", throwable.getMessage());
        } else {
            getView().showError("Could not create trekking plan", throwable.getMessage());
        }
    }

    private void _onCreateCreatePlanSucceeded(int planId) {
        getView().setLoading(false);
        getView().closeWindow();
    }
}

package com.phuongkhanh.youmetrips.presentation.components.trekingplan;

import com.phuongkhanh.youmetrips.presentation.exceptions.ExistedUserCreatePlanToPlaceException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiException;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;

public class TrekkingPlanPresenter extends PresenterBase<TrekkingPlanScreen> {

    private final TrekkingPlanService _service;

    @Inject
    public TrekkingPlanPresenter(TrekkingPlanService service) {
        _service = service;
    }

    public void requestNavigateBack() {
        assert (getView() != null);
        getView().navigateBack();
    }

    public void createCreatePlan(CreatePlan plan) {
        assert (getView() != null);
        getView().setLoading(true);

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() {
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

    private int _doCreateCreatePlan(CreatePlan CreatePlan) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();

        return _service.createTrekkingPlan(userId, jwt, CreatePlan);
    }

    private void _onCreateCreatePlanFailed(Throwable throwable) {
        getView().setLoading(false);

        if (throwable instanceof ApiException) {
            getView().showError("Could not create trekking plan", "Could not connect to server, please check your connection");
        } else if (throwable instanceof ExistedUserCreatePlanToPlaceException) {
            getView().showError("Could not create trekking plan", throwable.getMessage());
        } else {
            getView().showError("Could not create trekking plan", throwable.getMessage());
        }
    }

    private void _onCreateCreatePlanSucceeded(int planId) {
        getView().setLoading(false);
        getView().returnPlan(planId);
    }
}

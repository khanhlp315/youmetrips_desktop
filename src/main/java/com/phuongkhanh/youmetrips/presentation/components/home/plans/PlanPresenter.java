package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetails;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetailsPlace;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.List;

public class PlanPresenter extends PresenterBase<PlanScreen> {
    private final PlanService _service;

    @Inject
    public PlanPresenter(PlanService service) {
        _service = service;
    }

    public void fetchPlans() {
        assert (getView() != null);

        HomeStore homeStore = _service.getHomeStore();
        List<RelevantPlan> plans = homeStore.getAllRelevantPlans();

        if (plans != null) {
            getView().updatePlans(plans);
            return;
        }

        Task<List<RelevantPlan>> task = new Task<List<RelevantPlan>>() {
            @Override
            protected List<RelevantPlan> call() {
                return _doFetchPlans();
            }

            @Override
            protected void failed() {
                _onFetchPlansFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<RelevantPlan> result = task.getValue();
            _onFetchPlansSucceeded(result);
        });
        new Thread(task).start();
    }

    private List<RelevantPlan> _doFetchPlans() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchPlans(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchPlansSucceeded(List<RelevantPlan> plans) {
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storePlans(plans);

        for (RelevantPlan relevantPlan : plans) {
            homeStore.addPlanDetails(
                    new PlanDetails(
                            relevantPlan.getPlanId(),
                            relevantPlan.getWhenToGoMin(),
                            relevantPlan.getWhenToGoMax(),
                            relevantPlan.getHowLongMin(),
                            relevantPlan.getHowLongMax(),
                            relevantPlan.getHotelLevel(),
                            relevantPlan.getDescription(),
                            new PlanDetailsPlace(
                                    relevantPlan.getPlace().getId(),
                                    relevantPlan.getPlace().getName(),
                                    relevantPlan.getPlace().getCoverImageUrl(),
                                    relevantPlan.getPlace().getTags()),
                            relevantPlan.getUserId(),
                            relevantPlan.getUserFirstName(),
                            relevantPlan.getUserLastName(),
                            relevantPlan.getUserAvatarUrl(),
                            relevantPlan.getUserOccupation())
            );

        }
        getView().updatePlans(plans);
    }

    private void _onFetchPlansFailed(Throwable throwable) {
    }

    public void requestNavigateToCreateTrekkingPlan() {
        getView().navigateToCreateTrekkingPlan();
    }

    public void requestNavigateToCreateTrekkingPlace()
    {
        assert (getView() != null);
        getView().navigateToCreateTrekkingPlace();
    }

    public void refreshPlans() {
        Task<List<RelevantPlan>> task = new Task<List<RelevantPlan>>() {
            @Override
            protected List<RelevantPlan> call() {
                return _doFetchPlans();
            }

            @Override
            protected void failed() {
                _onFetchPlansFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<RelevantPlan> result = task.getValue();
            _onFetchPlansSucceeded(result);
        });
        new Thread(task).start();
    }

    public void requestShowAddFriendPopup(int userId) {
        getView().showAddFriendPopup(userId);
    }

    public void addFriend(int userId) {
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() {
                _doAddFriend(userId);
                return null;
            }

            @Override
            protected void failed() {
                _onAddFriendFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onAddFriendSucceeded();
        });
        new Thread(task).start();
    }

    private void _doAddFriend(int userId) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        _service.sendFriendRequest(userId, authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onAddFriendSucceeded() {
    }

    private void _onAddFriendFailed(Throwable throwable) {
    }

    public void requestNavigateToPlanDetails(int planId) {
        assert (getView() != null);
        getView().navigateToPlanDetails(planId);
    }

    public void requestNavigateToPlace() {
        assert (getView() != null);
        getView().navigateToPlace();
    }

    public void requestNavigateToProfile() {
        assert (getView() != null);
        getView().navigateToProfile();
    }

    public void requestNavigateToEditProfile() {
        assert (getView() != null);
        getView().navigateToEditProfile();
    }

    public void requestNavigateToFriendRequest() {
        assert (getView() != null);
        getView().navigateToFriendRequest();
    }

}

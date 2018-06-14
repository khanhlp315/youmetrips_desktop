package com.phuongkhanh.youmetrips.presentation.components.planlist;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetails;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetailsPlace;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;

public class PlanListPresenter extends PresenterBase<PlanListScreen> {
    private final PlanListService _service;

    @Inject
    public PlanListPresenter(PlanListService service) {
        _service = service;
    }

    public void requestNavigateBack() {
        getView().navigateBack();
    }

    public void fetchPlans(int userId) {
        assert (getView() != null);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        Profile profile = authenticationStore.getProfile();

        if (profile != null) {
            getView().updatePlans(profile.getTrekkingPlanSet());
            return;
        }

        Task<Profile> task = new Task<Profile>() {
            @Override
            protected Profile call() {
                return doFetchProfile();
            }

            @Override
            protected void failed() {
                onFetchUserFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            onFetchUserSuceeded(task.getValue());
        });
    }

    private Profile doFetchProfile() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        Profile profile = _service.getUserProfile(authenticationStore.getUserId(), authenticationStore.getJwt());
        return profile;
    }

    private void onFetchUserSuceeded(Profile profile) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeProfile(profile);

        HomeStore homeStore = _service.getHomeStore();

        profile.getTrekkingPlanSet().forEach(plan -> {
            homeStore.addPlanDetails(
                    new PlanDetails(
                            plan.getId(),
                            plan.getWhenToGoMin(),
                            plan.getWhenToGoMax(),
                            plan.getHowLongMin(),
                            plan.getHowLongMax(),
                            plan.getHotelLevel(),
                            plan.getDescription(),
                            new PlanDetailsPlace(
                                    plan.getPlace().getId(),
                                    plan.getPlace().getName(),
                                    plan.getPlace().getCoverImageUrl(),
                                    plan.getPlace().getTags()
                            ),
                            profile.getUserId(),
                            profile.getFirstName(),
                            profile.getLastName(),
                            profile.getAvatar(),
                            profile.getOccupation()
                    )
            );

            getView().updatePlans(profile.getTrekkingPlanSet());
        });
    }

    void onFetchUserFailed(Throwable ex) {

    }

    void requestNavigateToPlanDetails(int id) {
        assert (getView() != null);
        getView().navigateToPlanDetails(id);
    }
}

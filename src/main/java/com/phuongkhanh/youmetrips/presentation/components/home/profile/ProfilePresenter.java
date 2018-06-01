package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.*;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import javax.inject.Inject;
import java.util.List;

public class ProfilePresenter extends PresenterBase<ProfileScreen> {
    private final ProfileService _service;

    @Inject
    public ProfilePresenter(ProfileService service) {
        _service = service;
    }

    void fetchProfile() {
        assert (getView() != null);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        Profile profile = authenticationStore.getProfile();

        if (profile != null) {
            getView().updateProfile(profile);
            return;
        }

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doFetchProfile();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onFetchUserSucceeded(profile);
                        //getView().setLoading(false);
                    }

                    @Override
                    protected void failed() {
                        _onFetchUserFailed(getException());
                        //getView().setLoading(false);
                    }
                }
        ).start();
    }

    void fetchFriends() {
        assert (getView() != null);

        HomeStore homeStore = _service.getHomeStore();
        List<Friend> friends = homeStore.getAllFriends();

        if (friends != null) {
            getView().updateFriends(friends);
        }

        Task<List<Friend>> task = new Task<List<Friend>>() {
            @Override
            protected List<Friend> call() throws Exception {
                return _doFetchFriends();
            }

            @Override
            protected void failed() {
                _onFetchFriendsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<Friend> result = task.getValue();
            _onFetchFriendsSucceeded(friends);
        });

        new Thread(task).start();
    }

    private Profile _doFetchProfile() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.getUserProfile(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchUserSucceeded(Profile user) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeProfile(user);

        HomeStore homeStore = _service.getHomeStore();

        for (UserTrekkingPlan plan : user.getTrekkingPlanSet()) {
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
                                    plan.getPlace().getTags()),
                            user.getUserId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getAvatar(),
                            user.getOccupation()
                    )
            );
        }
        getView().updateProfile(user);

    }

    private void _onFetchUserFailed(Throwable throwable) {
    }

    private List<Friend> _doFetchFriends() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchAllFriends(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchFriendsSucceeded(List<Friend> friends) {
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storeFriends(friends);

        getView().updateFriends(friends);
    }

    private void _onFetchFriendsFailed(Throwable throwable) {
    }

    public void logOut() {
        _service.getHomeStore().clearData();
        _service.getAuthenticationStore().clearLoginData();
        getView().navigateToStart();
    }

    public void refreshUser() {
        Task<Profile> task = new Task<Profile>() {
            @Override
            protected Profile call() throws Exception {
                return _doFetchProfile();
            }

            @Override
            protected void failed() {
                _onFetchUserFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            Profile result = task.getValue();
            _onFetchUserSucceeded(result);
        });
        new Thread(task).start();
    }

    public void requestNavigateToCreateTrekkingPlan() {
        assert (getView() != null);
        getView().navigateToCreateTrekkingPlan();
    }

    public void requestEditProfile() {
        assert (getView() != null);
        getView().navigateToEditProfile();
    }

    public void refreshFriends() {
        Task<List<Friend>> task = new Task<List<Friend>>() {
            @Override
            protected List<Friend> call() throws Exception {
                return _doFetchFriends();
            }

            @Override
            protected void failed() {
                _onFetchFriendsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<Friend> result = task.getValue();
            _onFetchFriendsSucceeded(result);
        });

        new Thread(task).start();
    }

    public void requestNavigateToFriendList() {
        assert (getView() != null);
        getView().navigateToFriendList();
    }

    public void requestNavigateToPlanList() {
        getView().navigateToPlanList();
    }

    public void requestNavigateToPlanDetails(int id) {
        assert (getView() != null);
        getView().navigateToPlanDetails(id);
    }
}

package com.phuongkhanh.youmetrips.presentation.components.home.profile;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.*;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;

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

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doFetchFriends();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onFetchFriendsSucceeded(friends);
                    }

                    @Override
                    protected void failed() {
                        _onFetchFriendsFailed(getException());
                    }
                }
        ).start();
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
        try {
            _onFetchUserSucceeded(_doFetchProfile());
        } catch (Exception e) {
            _onFetchUserFailed(e);
        }
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
        try {
            _onFetchFriendsSucceeded(_doFetchFriends());
        } catch (Exception e) {
            _onFetchFriendsFailed(e);
        }
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

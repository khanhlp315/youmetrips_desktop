package com.phuongkhanh.youmetrips.presentation.components.friendlist;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.Friend;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.List;

public class FriendListPresenter extends PresenterBase<FriendListScreen> {
    private final FriendListService _service;

    @Inject
    public FriendListPresenter(FriendListService service) {
        _service = service;
    }

    public void requestNavigateBack() {
        getView().navigateBack();
    }

    //region fetch friends
    public void fetchFriends() {
        assert (getView() != null);

        HomeStore homeStore = _service.getHomeStore();
        List<Friend> friends = homeStore.getAllFriends();
        if (friends != null) {
            getView().updateFriends(friends);
            return;
        }

        Task<List<Friend>> task = new Task<List<Friend>>() {
            @Override
            protected List<Friend> call() {
                return doFetchFriends();
            }

            @Override
            protected void failed() {
                onFetchFriendsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            onFetchFriendsSucceeded(task.getValue());
        });

        new Thread(task).start();
    }

    private List<Friend> doFetchFriends() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchAllFriends(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void onFetchFriendsSucceeded(List<Friend> friends) {
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storeFriends(friends);
        getView().updateFriends(friends);
    }

    private void onFetchFriendsFailed(Throwable ex) {

    }

    //endregion
}

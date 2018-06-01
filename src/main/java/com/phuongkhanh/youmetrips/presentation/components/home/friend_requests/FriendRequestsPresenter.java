package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.List;

public class FriendRequestsPresenter extends PresenterBase<FriendRequestsScreen> {
    private final FriendRequestsService _service;

    @Inject
    public FriendRequestsPresenter(FriendRequestsService service)
    {
        _service = service;
    }

    public void fetchFriendRequests(){
        assert(getView() != null);

        HomeStore homeStore = _service.getHomeStore();

        List<FriendRequest> friendRequests = homeStore.getAllFriendRequests();
        if(friendRequests != null){
            getView().updateRequests(friendRequests);
            return;
        }

        Task<List<FriendRequest>> task = new Task<List<FriendRequest>>() {
            @Override
            protected List<FriendRequest> call() throws Exception {
                return _doFetchFriendRequests();
            }

            @Override
            protected void failed() {
                _onFetchFriendRequestsFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<FriendRequest> result = task.getValue();
            _onFetchFriendRequestsSucceeded(result);
        });
        new Thread(task).start();
    }

    private List<FriendRequest> _doFetchFriendRequests() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchAllFriendRequests(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchFriendRequestsSucceeded(List<FriendRequest> requests){
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storeFriendRequests(requests);
        getView().updateRequests(requests);
    }

    private void _onFetchFriendRequestsFailed(Throwable throwable){

    }

    public void refreshRequests() {
        Task<List<FriendRequest>> task = new Task<List<FriendRequest>>() {
            @Override
            protected List<FriendRequest> call() throws Exception {
                return _doFetchFriendRequests();
            }

            @Override
            protected void failed() {
                _onFetchFriendRequestsFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<FriendRequest> result = task.getValue();
            _onFetchFriendRequestsSucceeded(result);
        });
        new Thread(task).start();
    }

    public void acceptRequest(int id) {
        assert(getView() != null);

        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                _doAcceptRequest(id);
                return null;
            }

            @Override
            protected void failed() {
                _onAcceptRequestFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onAcceptRequestSuceeded(id);
        });
        new Thread(task).start();
    }

    private void _doAcceptRequest(int id) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.acceptRequest(id, authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onAcceptRequestSuceeded(int id){
        HomeStore homeStore = _service.getHomeStore();
        homeStore.removeRequest(id);
    }

    private void _onAcceptRequestFailed(Throwable throwable){
    }

    public void declineRequest(int id) {
        assert(getView() != null);

        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                _doDeclineRequest(id);
                return null;
            }

            @Override
            protected void failed() {
                _onDeclineRequestFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onDeclineRequestSuceeded(id);
        });
        new Thread(task).start();
    }

    private void  _doDeclineRequest(int id) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.declineRequest(id, authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onDeclineRequestSuceeded(int id){
        HomeStore homeStore = _service.getHomeStore();
        homeStore.removeRequest(id);
        getView().removeRequest(id);
    }

    private void _onDeclineRequestFailed(Throwable throwable){

    }
}

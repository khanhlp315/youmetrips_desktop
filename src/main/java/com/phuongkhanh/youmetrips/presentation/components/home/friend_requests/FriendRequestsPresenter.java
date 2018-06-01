package com.phuongkhanh.youmetrips.presentation.components.home.friend_requests;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
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

        _doFetchFriendRequests()
                .then((requests){
                        _onFetchFriendRequestsSucceeded(requests);
    })
        .catchError((error){
                _onFetchFriendRequestsFailed(error);
    });

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
        return _doFetchFriendRequests()
                .then((requests){
                        _onFetchFriendRequestsSucceeded(requests);
    })
        .catchError((error){
                _onFetchFriendRequestsFailed(error);
    }).then((_){
        return null;
    });

    }

    public void acceptRequest(int id) {
        assert(getView() != null);

        _doAcceptRequest(id)
                .then((_){
                        _onAcceptRequestSuceeded(id);
    })
        .catchError((error){
                _onAcceptRequestFailed(error);
    });
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

        _doDeclineRequest(id)
                .then((_){
                        _onDeclineRequestSuceeded(id);
    })
        .catchError((error){
                _onDeclineRequestFailed(error);
    });
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

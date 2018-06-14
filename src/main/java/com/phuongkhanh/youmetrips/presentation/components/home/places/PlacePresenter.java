package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.List;

public class PlacePresenter extends PresenterBase<PlaceScreen> {
    private final PlaceService _service;

    @Inject
    public PlacePresenter(PlaceService service) {
        _service = service;
    }

    public void fetchPlaces() {
        assert (getView() != null);

        HomeStore homeStore = _service.getHomeStore();

        List<Place> places = homeStore.getAllPlaces();
        if (places != null) {
            getView().updatePlaces(places);
            return;
        }

        Task<List<Place>> task = new Task<List<Place>>() {
            @Override
            protected List<Place> call() {
                return _doFetchPlaces();
            }

            @Override
            protected void failed() {
                _onFetchPlacesFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<Place> result = task.getValue();
            _onFetchPlacesSucceeded(result);
        });
        new Thread(task).start();
    }

    public void refreshPlaces() {
        Task<List<Place>> task = new Task<List<Place>>() {
            @Override
            protected List<Place> call() {
                return _doFetchPlaces();
            }

            @Override
            protected void failed() {
                _onFetchPlacesFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            List<Place> result = task.getValue();
            _onFetchPlacesSucceeded(result);
        });
        new Thread(task).start();
    }

    private List<Place> _doFetchPlaces() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchPlaces(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchPlacesSucceeded(List<Place> places) {
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storePlaces(places);
        getView().updatePlaces(places);
    }

    private void _onFetchPlacesFailed(Throwable throwable) {
    }

    public void requestNavigateToCreateTrekkingPlan()
    {
        assert (getView() != null);
        getView().navigateToCreateTrekkingPlan();
    }

    public void requestNavigateToCreateTrekkingPlace()
    {
        assert (getView() != null);
        getView().navigateToCreateTrekkingPlace();
    }

    public void like(int id) {
        assert (getView() != null);
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() {
                _doLike(id);
                return null;
            }

            @Override
            protected void failed() {
                _onLikeFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onLikeSucceeded(id);
        });
        new Thread(task).start();
    }

    public void unlike(int id) {
        assert (getView() != null);
        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() {
                _doUnlike(id);
                return null;
            }

            @Override
            protected void failed() {
                _onUnlikeFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onUnlikeSucceeded(id);
        });
        new Thread(task).start();
    }

    private void _doLike(int id) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();

        _service.like(userId, id, jwt);
    }

    private void _onLikeSucceeded(int id) {
        getView().onLiked(id);
    }

    private void _onLikeFailed(Throwable throwable) {

    }

    private void _doUnlike(int id) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();

        _service.unlike(userId, id, jwt);
    }

    private void _onUnlikeSucceeded(int id) {
        getView().onUnliked(id);
    }

    private void _onUnlikeFailed(Throwable throwable) {

    }

    public void requestNavigateToPlan() {
        assert (getView() != null);
        getView().navigateToPlan();
    }

    public void requestNavigateToProfile() {
        assert (getView() != null);
        getView().navigateToProfile();
    }

    public void requestNavigateToFriendRequest() {
        assert (getView() != null);
        getView().navigateToFriendRequest();
    }

    public void requestNavigateToEditProfile() {
        assert (getView() != null);
        getView().navigateToEditProfile();
    }
}

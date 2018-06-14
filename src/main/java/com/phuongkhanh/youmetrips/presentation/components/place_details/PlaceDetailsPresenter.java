package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreateTag;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.api.models.Profile;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;

public class PlaceDetailsPresenter extends PresenterBase<PlaceDetailsScreen> {

    private final PlaceDetailsService _service;

    @Inject
    public PlaceDetailsPresenter(PlaceDetailsService service) {
        _service = service;
    }

    private PlaceDetails _doGetPlaceDetails(int placeId) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.getPlaceDetails(authenticationStore.getUserId(), placeId, authenticationStore.getJwt());
    }

    private void _onGetPlaceDetailsSucceeded(PlaceDetails placeDetails) {
        HomeStore homeStore = _service.getHomeStore();

        homeStore.addPlaceDetails(placeDetails);
        getView().updatePlaceInfo(placeDetails);
        getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
    }

    private void _onGetPlaceDetailsFailed(Throwable throwable) {

    }

    public void requestNavigateBack() {
        assert (getView() != null);
        getView().navigateBack();
    }

    public void goNow() {
        assert (getView() != null);
        getView().navigateToCreateTrekkingPlan();
    }

    public void fetchPlaceDetails(int placeId) {
        HomeStore homeStore = _service.getHomeStore();

        PlaceDetails placeDetails = homeStore.getPlaceDetails(placeId);

        if (placeDetails != null) {
            getView().updatePlaceInfo(placeDetails);
            getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
            return;
        }
        Task<PlaceDetails> task = new Task<PlaceDetails>() {
            @Override
            protected PlaceDetails call() {
                return _doGetPlaceDetails(placeId);
            }

            @Override
            protected void failed() {
                _onGetPlaceDetailsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onGetPlaceDetailsSucceeded(task.getValue());
        });
        new Thread(task).start();
    }

    public void onUserRatingChanged(int rate) {
        getView().updateUserRating(rate);
    }

//    void popupReviewDialog(TextEditingController controller) {
//        _view.popupDialog("Review", "message", controller);
//    }

    public void refreshPlaceDetails(int placeId) {
        Task<PlaceDetails> task = new Task<PlaceDetails>() {
            @Override
            protected PlaceDetails call() {
                return _doGetPlaceDetails(placeId);
            }

            @Override
            protected void failed() {
                _onGetPlaceDetailsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            PlaceDetails result = task.getValue();
            _onGetPlaceDetailsSucceeded(result);
        });
        new Thread(task).start();
    }

    public void rate(int placeId, int rate, String message) {
        Task<Object> task = new Task<Object>() {
            @Override
            protected PlaceDetails call() {
                _doRate(placeId, rate, message);
                return null;
            }

            @Override
            protected void failed() {
                _onRateFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onRateSucceeded(placeId);
        });
        new Thread(task).start();

    }

    private void _doRate(int placeId, int rate, String message) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.review(rate, message, authenticationStore.getUserId(), placeId, authenticationStore.getJwt());
    }

    private void _onRateSucceeded(int placeId) {
        refreshPlaceDetails(placeId);
    }

    private void _onRateFailed(Throwable throwable) {

    }

    public void like(int id) {
        assert (getView() != null);
        Task<Object> task = new Task<Object>() {
            @Override
            protected PlaceDetails call() {
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

    private void _doLike(int id) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();
        _service.like(userId, id, jwt);
    }

    private void _onLikeSucceeded(int id) {
        getView().onLiked();
    }

    private void _onLikeFailed(Throwable throwable) {

    }

    public void addTag(int placeId, CreateTag tag) {
        Task<Object> task = new Task<Object>() {
            @Override
            protected PlaceDetails call() {
                _doAddTag(placeId, tag);
                return null;
            }

            @Override
            protected void failed() {
                _onAddTagFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onAddTagSucceeded(placeId);
        });
        new Thread(task).start();
    }

    private void _doAddTag(int placeId, CreateTag tag) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        _service.addTag(tag, authenticationStore.getUserId(), placeId, authenticationStore.getJwt());
    }

    private void _onAddTagSucceeded(int placeId) {
        refreshPlaceDetails(placeId);
    }

    private void _onAddTagFailed(Throwable throwable) {

    }

    public void fetchAvatar() {
        assert (getView() != null);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        Profile profile = authenticationStore.getProfile();
        if (profile != null) {
            getView().updateUserAvatar(profile.getAvatar());
            return;
        }

        Task<Profile> task = new Task<Profile>() {
            @Override
            protected Profile call() {
                return _doFetchAvatar();
            }

            @Override
            protected void failed() {
                _onFetchUserFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            Profile res = task.getValue();
            _onFetchUserSucceeded(res);
        });
        new Thread(task).start();
    }

    private Profile _doFetchAvatar() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.getUserProfile(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchUserSucceeded(Profile user) {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeProfile(user);

        getView().updateUserAvatar(user.getAvatar());
    }

    private void _onFetchUserFailed(Throwable throwable) {
    }

    public int getPlaceId()
    {
        return _service.getHomeStore().getPlaceDetailsId();
    }
}

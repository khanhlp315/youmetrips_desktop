package com.phuongkhanh.youmetrips.presentation.components.home.places;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;

import javax.inject.Inject;
import java.util.List;

public class PlacePresenter extends PresenterBase<PlaceScreen> {
    private final PlaceService _service;

    @Inject
    public PlacePresenter(PlaceService service)
    {
        _service = service;
    }

    void fetchPlaces() {
        assert(getView() != null);

        HomeStore homeStore = _service.getHomeStore();

        List<Place> places = homeStore.getAllPlaces();
        if(places != null){
            getView().updatePlaces(places);
            return;
        }

        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doFetchPlaces();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onFetchPlacesSucceeded(places);
                    }

                    @Override
                    protected void failed() {
                        _onFetchPlacesFailed(getException());
                    }
                }
        ).start();
    }

    public void refreshPlaces(){
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doFetchPlaces();
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onFetchPlacesSucceeded(_doFetchPlaces());
                    }

                    @Override
                    protected void failed() {
                        _onFetchPlacesFailed(getException());
                    }
                }
        ).start();
    }

    private List<Place> _doFetchPlaces() {
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        return _service.fetchPlaces(authenticationStore.getUserId(), authenticationStore.getJwt());
    }

    private void _onFetchPlacesSucceeded(List<Place> places){
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storePlaces(places);
        getView().updatePlaces(places);
    }

    private void _onFetchPlacesFailed(Throwable throwable){
    }

    public void requestNavigateToCreateTrekkingPlace() {
        getView().navigateToCreateTrekkingPlace();
    }

    public void like(int id) {
        assert(getView() != null);
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doLike(id);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onLikeSucceeded(id);
                    }

                    @Override
                    protected void failed() {
                        _onLikeFailed(getException());
                    }
                }
        ).start();
    }

    public void unlike(int id) {
        assert(getView() != null);
        new Thread(
                new Task<Object>() {
                    @Override
                    protected Object call() throws Exception {
                        _doUnlike(id);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        _onUnlikeSucceeded(id);
                    }

                    @Override
                    protected void failed() {
                        _onUnlikeFailed(getException());
                    }
                }
        ).start();
    }

    private void _doLike(int id)  {
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

}

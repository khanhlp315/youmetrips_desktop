package com.phuongkhanh.youmetrips.presentation.components.plandetails;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.*;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.util.List;

public class PlanDetailsPresenter extends PresenterBase<PlanDetailsScreen> {
    private final PlanDetailsService _service;

    @Inject
    public PlanDetailsPresenter(PlanDetailsService service) {
        _service = service;
    }

    public void requestNavigateBack(){
        getView().navigateBack();
    }

    public void fetchPlanDetails(int planId){
        assert(getView() != null);
        HomeStore homeStore = _service.getHomeStore();

        PlanDetails planDetails = homeStore.getPlanDetails(planId);

        assert(planDetails != null);

        getView().updatePlanDetails(planDetails);

        fetchLocation(planDetails.getPlace().getId());
    }

    //region get location
    private void fetchLocation(int placeId){
        HomeStore homeStore = _service.getHomeStore();
        PlaceDetails placeDetails = homeStore.getPlaceDetails(placeId);

        if(placeDetails != null){
            getView().updateMapUrl(_service.getMapUrl(placeDetails.getLocation()));
            return;
        }

        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return doGetLocation(placeId);
            }

            @Override
            protected void failed() {
                onGetLocationFailed(getException());
            }
        } ;
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event ->{
            onGetLocationSucceded(task.getValue());
        });

        new Thread(task).start();
    }

    private String doGetLocation(int placeId){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        PlaceDetails placeDetails = _service.getPlaceDetails(authenticationStore.getUserId(), placeId, authenticationStore.getJwt());
        return placeDetails.getLocation();
    }

    private void onGetLocationSucceded(String location){
        getView().updateMapUrl(_service.getMapUrl(location));
    }

    private void onGetLocationFailed(Throwable ex){

    }
    //endregion

    //region fetch comments
    public void fetchComments(int planId){
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();

        List<Comment> comments = homeStore.getComments(planId);

        if(comments != null){
            getView().updateComments(comments);
            return;
        }

        Task<List<Comment>> task = new Task<List<Comment>>() {
            @Override
            protected List<Comment> call() throws Exception {
                return doFetchComments(planId);
            }

            @Override
            protected void failed() {
                onFetchCommentsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event->{
            onFetchCommentsSucceeded(task.getValue(), planId);
        });

        new Thread(task).start();
    }

    private List<Comment> doFetchComments(int planId){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        List<Comment> comments = _service.fetchComments(authenticationStore.getUserId(), planId, authenticationStore.getJwt());
        return comments;
    }

    private void onFetchCommentsSucceeded(List<Comment> comments, int planId){
        HomeStore homeStore = _service.getHomeStore();
        homeStore.storeComments(comments, planId);
        getView().updateComments(comments);
    }

    private void onFetchCommentsFailed(Throwable ex){

    }
    //endregion

    //region post comment
    public void postComment(String message, int planId){
        assert (getView() != null);

        Task<Object> task = new Task<Object>() {
            @Override
            protected Object call() throws Exception {
                doPostComment(message, planId);
                return null;
            }

            @Override
            protected void succeeded() {
                onPostCommentSucceeded(planId);
            }

            @Override
            protected void failed() {
                onPostCommentFailed(getException());
            }
        };
        new Thread(task).start();
    }

    private void doPostComment(String message, int planId){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        _service.postComment(message, authenticationStore.getUserId(), planId, authenticationStore.getJwt());
    }

    private void onPostCommentSucceeded(int planId){
        getView().resetCommentTextField();

        Task<List<Comment>> task = new Task<List<Comment>>() {
            @Override
            protected List<Comment> call() throws Exception {
                return doFetchComments(planId);
            }

            @Override
            protected void failed() {
                onFetchCommentsFailed(getException());
            }
        };

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event->{
            onFetchCommentsSucceeded(task.getValue(), planId);
        });

        new Thread(task).start();
    }

    private void onPostCommentFailed(Throwable ex){

    }
    //endregion

    //region fetch avatar
    public void fetchAvatar(){
        assert (getView() != null);

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        Profile profile = authenticationStore.getProfile();
        if(profile != null){
            getView().updateUserAvatar(profile.getAvatar());
        }

        Task<Profile> task = new Task<Profile>() {
            @Override
            protected Profile call() throws Exception {
                return doFetchAvatar();
            }

            @Override
            protected void failed() {
                onFetchAvatarFailed(getException());
            }
        }
        ;

        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event->{
            onFetchAvatarSucceeded(task.getValue());
        });

        new Thread(task).start();
    }

    private Profile doFetchAvatar(){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        Profile profile = _service.getUserProfile(authenticationStore.getUserId(), authenticationStore.getJwt());
        return profile;
    }

    private void onFetchAvatarSucceeded(Profile profile){
        AuthenticationStore authenticationStore = _service.getAuthenticationStore();
        authenticationStore.storeProfile(profile);
        getView().updateUserAvatar(profile.getAvatar());
    }

    private void onFetchAvatarFailed(Throwable ex){
    }
    //endregion
}

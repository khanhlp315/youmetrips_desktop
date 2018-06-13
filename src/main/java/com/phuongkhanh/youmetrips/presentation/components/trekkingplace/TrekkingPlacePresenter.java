package com.phuongkhanh.youmetrips.presentation.components.trekkingplace;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiException;
import com.phuongkhanh.youmetrips.presentation.exceptions.EmptyRequiredFieldException;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrekkingPlacePresenter extends PresenterBase<TrekkingPlaceScreen> {
    private final TrekkingPlaceService _service;
    
    @Inject
    public TrekkingPlacePresenter(TrekkingPlaceService service)
    {
        _service = service;
    }

    public void requestNavigateBack() {
        assert(getView() != null);
        getView().navigateBack();
    }

    public void createTrekkingPlace(String placeName, String placeLocation, File coverPhoto, List<File> otherPhotos, List<String> hashtags) {
        assert(getView() != null);
        getView().setLoading(true);

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return _doCreateTrekkingPlace(placeName, placeLocation, coverPhoto, otherPhotos, hashtags);
            }

            @Override
            protected void failed() {
                _onCreateTrekkingPlaceFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            Integer id = task.getValue();
            _onCreateTrekkingPlaceSucceeded(id);
        });
        new Thread(task).start();
    }

    private int _doCreateTrekkingPlace(String placeName, String placeLocation, File coverPhoto, List<File> otherPhotos, List<String> hashtags) {
        if(placeName.trim().equals("") || placeLocation.trim().equals("")){
            throw new EmptyRequiredFieldException();
        }

        if(coverPhoto == null){
            throw new EmptyRequiredFieldException();
        }

        AuthenticationStore authenticationStore = _service.getAuthenticationStore();

        int userId = authenticationStore.getUserId();
        String jwt = authenticationStore.getJwt();

        String coverImageUrl = _service.uploadFile(userId, jwt, coverPhoto);
        List<String> otherPhotosUrls = new ArrayList<String>();

        for(File photo: otherPhotos)
        {
            otherPhotosUrls.add(_service.uploadFile(userId, jwt, photo));
        }

        CreatePlace trekkingPlace = new CreatePlace(
                placeName,
                placeLocation,
                coverImageUrl,
                otherPhotosUrls,
                hashtags
    );

        return _service.createPlace(userId, jwt, trekkingPlace);
    }

    private void _onCreateTrekkingPlaceFailed(Throwable throwable) {
        getView().setLoading(false);

        if(throwable instanceof ApiException){
            getView().showError("Could not create trekking place", "Could not connect to server, please check your connection");
        }
    else{
            getView().showError("Could not create trekking place", throwable.getMessage());
        }
    }

    private void _onCreateTrekkingPlaceSucceeded(int placeId) {
        getView().setLoading(false);
        getView().returnPlace(placeId);
    }
}

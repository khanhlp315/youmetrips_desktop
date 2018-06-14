package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.presentation.exceptions.EmptyRequiredFieldException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.exceptions.ApiException;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.stores.AuthenticationStore;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrekkingPlaceHashtagsPresenter extends PresenterBase<TrekkingPlaceHashtagsScreen> {

    private final TrekkingPlaceHashtagsService _service;

    private List<String> _currentHashtags;

    @Inject
    public TrekkingPlaceHashtagsPresenter(TrekkingPlaceHashtagsService service) {
        _service = service;
        _currentHashtags = new ArrayList<>();
    }

    public void onInputUpdated(List<String> hashtags) {
        assert (getView() != null);
        if (_isValidInput(hashtags)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }
    }

    private boolean _isValidInput(List<String> hashtags) {
        return hashtags.size() != 0;
    }

    public void requestAddHashtag(String text) {
        assert (getView() != null);

        if (text.trim().equals("")) {
            return;
        }

        if (_currentHashtags.contains(text)) {
            return;
        }

        if (text.contains(" "))
            return;

        getView().addHashtag(text);
        _currentHashtags.add(text);
    }

    public void requestRemoveHashtag(String hashtag) {
        assert (getView() != null);

        getView().removeHashtag(hashtag);
    }


    public void requestCreatePlace(List<String> hashtags) {
        assert (getView() != null);
        getView().setLoading(true);

        CreatePlace place = _service.getHomeStore().getCreatePlace();
        place.setTags(hashtags);

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return _doCreateTrekkingPlace(place);
            }
            @Override
            protected void failed() {
                _onCreateTrekkingPlaceFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onCreateTrekkingPlaceSucceeded(task.getValue());
        });
        new Thread(task).start();
    }

    private int _doCreateTrekkingPlace(CreatePlace place) {
        if (place.getName().trim().equals("") || place.getLocation().trim().equals("")) {
            throw new EmptyRequiredFieldException();
        }

        if (place.getCoverImageUrl() == null) {
            throw new EmptyRequiredFieldException();
        }

        return _service.createPlace(_service.getAuthenticationStore().getUserId(), _service.getAuthenticationStore().getJwt(), place);
    }

    private void _onCreateTrekkingPlaceFailed(Throwable throwable) {
        getView().setLoading(false);

        if (throwable instanceof ApiException) {
            getView().showError("Could not create trekking place", "Could not connect to server, please check your connection");
        } else {
            getView().showError("Could not create trekking place", throwable.getMessage());
        }
    }

    private void _onCreateTrekkingPlaceSucceeded(int placeId) {
        getView().setLoading(false);
        getView().returnPlace(placeId);
        getView().getWindow().close();
    }
}

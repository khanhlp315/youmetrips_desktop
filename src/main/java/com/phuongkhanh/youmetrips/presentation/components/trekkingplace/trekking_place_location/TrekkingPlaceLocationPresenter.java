package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;

public class TrekkingPlaceLocationPresenter extends PresenterBase<TrekkingPlaceLocationScreen> {

    private final TrekkingPlaceLocationService _service;

    @Inject
    public TrekkingPlaceLocationPresenter(TrekkingPlaceLocationService service) {
        _service = service;
    }

    private boolean _isValidInput(String placeName) {
        return !placeName.trim().equals("");
    }

    public void requestShowMap() {
        assert (getView() != null);
        getView().showMap();
    }


    public void onLocationUpdated(String selectedPlace) {
        assert (getView() != null);
        if (_isValidInput(selectedPlace)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }
    }

    public void requestToNavigateToPhotos() {
        assert (getView() != null);
        getView().navigateToPhotos();
    }
}

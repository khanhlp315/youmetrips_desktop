package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import com.phuongkhanh.youmetrips.services.stores.HomeStore;

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


    public void onLocationUpdated(String selectedPlace) {
        assert (getView() != null);
        if (_isValidInput(selectedPlace)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }
    }

    public void requestToNavigateToPhotos(String selectedPlace) {
        assert (getView() != null);
        HomeStore homeStore = _service.getHomeStore();
        CreatePlace place = homeStore.getCreatePlace();
        place.setLocation(selectedPlace);
        getView().navigateToPhotos();
    }
}

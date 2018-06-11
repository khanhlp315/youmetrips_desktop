package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;

public class TrekkingPlaceNamePresenter extends PresenterBase<TrekkingPlaceNameScreen> {

    private final TrekkingPlaceNameService _service;

    @Inject
    public TrekkingPlaceNamePresenter(TrekkingPlaceNameService service) {
        _service = service;
    }

    public void onInputUpdated(String placeName) {
        assert (getView() != null);
        if (_isValidInput(placeName)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }
    }

    private boolean _isValidInput(String placeName) {
        return placeName.trim() != "";
    }

    public void requestToNavigateToLocation() {
        assert (getView() != null);
        getView().navigateToLocation();
    }
}

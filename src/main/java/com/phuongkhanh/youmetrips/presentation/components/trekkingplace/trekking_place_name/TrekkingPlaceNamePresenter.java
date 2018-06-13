package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

public class TrekkingPlaceNamePresenter extends PresenterBase<TrekkingPlaceNameScreen> {

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
}

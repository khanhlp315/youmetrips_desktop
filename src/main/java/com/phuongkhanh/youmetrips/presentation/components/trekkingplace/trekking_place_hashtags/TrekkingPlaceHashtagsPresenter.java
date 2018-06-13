package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import java.util.List;

public class TrekkingPlaceHashtagsPresenter extends PresenterBase<TrekkingPlaceHashtagsScreen> {

    public void onInputUpdated(List<String> hashtags) {
        assert (getView() != null);
        if (_isValidInput(hashtags)) {
            getView().showContinue();
        } else {
            getView().hideContinue();
        }
    }

    private boolean _isValidInput(List<String> hashtags) {
        return true;
    }

    public void requestAddHashtag(List<String> currentHashtags, String text) {
        assert (getView() != null);

        if (text.trim().equals("")) {
            return;
        }

        if (currentHashtags.contains(text)) {
            return;
        }


        getView().addHashtag(text);
    }

    public void requestRemoveHashtag(String hashtag) {
        assert (getView() != null);

        getView().removeHashtag(hashtag);
    }
}

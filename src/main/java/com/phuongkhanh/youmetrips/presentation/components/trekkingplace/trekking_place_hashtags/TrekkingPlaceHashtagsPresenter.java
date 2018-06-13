package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;
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

    public void onInputUpdated(List<String> hashtags){
        assert(getView() != null);
        if (_isValidInput(hashtags)){
            getView().showContinue();
        }
        else {
            getView().hideContinue();
        }
    }

    private boolean _isValidInput(List<String> hashtags) {
        return true;
    }

    public void requestAddHashtag(String text) {
        assert(getView() != null);

        if(text.trim().equals("")){
            return;
        }

        if(_currentHashtags.contains(text)){
            return;
        }

        if(text.contains(" "))
            return;

        getView().addHashtag(text);
        _currentHashtags.add(text);
    }

    public void requestRemoveHashtag(String hashtag) {
        assert(getView() != null);

        getView().removeHashtag(hashtag);
    }
}

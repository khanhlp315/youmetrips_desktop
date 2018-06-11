package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import javax.inject.Inject;
import java.io.File;

public class TrekkingPlacePhotosPresenter extends PresenterBase<TrekkingPlacePhotosScreen> {

    @Inject
    public TrekkingPlacePhotosPresenter() {
    }

    public void onCoverPhotoUpdated(File selectedPhoto) {
        assert (getView() != null);
        if (selectedPhoto == null) {
            getView().hideContinue();
        } else {
            getView().showContinue();
        }
    }

    public void pickImage(File image) {
        assert (getView() != null);
        if (image == null) {
            return;
        }
        getView().addImage(image);
    }

    public void requestRemoveImage(File image) {
        assert (getView() != null);
        getView().removeImage(image);
    }

    public void pickCoverImage(File image) {
        assert (getView() != null);

        if (image == null) {
            return;
        }
        getView().changeCoverImage(image);
    }

    public void requestRemoveCoverImage() {
        assert (getView() != null);
        getView().removeCoverImage();
    }

    public void requestToNavigateToHashtags() {
        assert (getView() != null);
        getView().navigateToHashTags();
    }
}


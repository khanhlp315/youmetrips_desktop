package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;

import java.io.File;

public class TrekkingPlacePhotosPresenter extends PresenterBase<TrekkingPlacePhotosScreen> {

    void onCoverPhotoUpdated(File selectedPhoto) {
        assert(getView() != null);
        if (selectedPhoto == null){
            getView().hideContinue();
        }
        else {
            getView().showContinue();
        }
    }

    void pickImage() {
        assert(getView() != null);
        var image = ImagePicker.pickImage();

        if(image == null){
            return;
        }

        getView().addImage(image);
    }

    void requestRemoveImage(File image) {
        assert(getView() != null);
        getView().removeImage(image);
    }

    Future pickCoverImage() async {
        assert(getView() != null);
        var image = await ImagePicker.pickImage();

        if(image == null){
            return;
        }

        getView().changeCoverImage(image);
    }

    void requestRemoveCoverImage() {
        assert(getView() != null);
        getView().removeCoverImage();
    }
}

package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.presentation.exceptions.EmptyRequiredFieldException;
import com.phuongkhanh.youmetrips.presentation.framework.PresenterBase;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlace;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import org.apache.commons.io.FilenameUtils;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TrekkingPlacePhotosPresenter extends PresenterBase<TrekkingPlacePhotosScreen> {

    private final TrekkingPlacePhotosService _service;

    @Inject
    public TrekkingPlacePhotosPresenter(TrekkingPlacePhotosService service) {
        _service = service;
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

        String extension = FilenameUtils.getExtension(image.getName());
        if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp"))
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

    public void requestToNavigateToHashtags(File coverImage, List<File> photos) {
        assert (getView() != null);

        if (photos != null)
            uploadPhotos(photos);

        if (coverImage != null)
            uploadCoverImage(coverImage);
        else
            getView().showError("Add Cover Image", "Cover Image can not null");

    }


    private void uploadCoverImage(File file) {
        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception {
                return _doUploadCoverImage(file);
            }

            @Override
            protected void failed() {
                _onUploadCoverImageFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onUploadCoverImageSucceed(task.getValue());
        });
        new Thread(task).start();
    }

    private String _doUploadCoverImage(File coverImage) {
        if (coverImage == null) {
            throw new EmptyRequiredFieldException();
        }
        return _service.uploadFile(_service.getAuthenticationStore().getUserId(), _service.getAuthenticationStore().getJwt(), coverImage);
    }

    private void _onUploadCoverImageSucceed(String coverImageUrl) {
        CreatePlace place = _service.getHomeStore().getCreatePlace();
        place.setCoverImageUrl(coverImageUrl);
        System.out.println(coverImageUrl);
        getView().navigateToHashTags();
    }

    private void _onUploadCoverImageFailed(Throwable throwable) {
        getView().showError("Failed to upload cover image", throwable.getMessage());
    }


    private void uploadPhotos(List<File> photos) {
        Task<List<String>> task = new Task<List<String>>() {
            @Override
            protected List<String> call() throws Exception {
                return _doUploadPhotos(photos);
            }

            @Override
            protected void failed() {
                _onUploadPhotosFailed(getException());
            }
        };
        task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event -> {
            _onUploadPhotosSucceed(task.getValue());
        });
        new Thread(task).start();
    }

    private List<String> _doUploadPhotos(List<File> photos) {
        List<String> list = new ArrayList<>();
        for (File file : photos)
            list.add(_service.uploadFile(_service.getAuthenticationStore().getUserId(), _service.getAuthenticationStore().getJwt(), file));
        return list;
    }

    private void _onUploadPhotosSucceed(List<String> photoUrls) {
        CreatePlace place = _service.getHomeStore().getCreatePlace();
        place.setPhotoUrls(photoUrls);
        System.out.println(photoUrls.toString());
    }

    private void _onUploadPhotosFailed(Throwable throwable) {
        getView().showError("Failed to upload photos", throwable.getMessage());
    }
}


package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlacePhotosScreenImpl extends FXMLScreen
implements TrekkingPlacePhotosScreen, Initializable {

    @Inject
    public TrekkingPlacePhotosScreenImpl(TrekkingPlacePhotosPresenter presenter)
    {

    }

    @Override
    public void showContinue() {

    }

    @Override
    public void hideContinue() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void addImage(File image) {

    }

    @Override
    public void removeImage(File image) {

    }

    @Override
    public void changeCoverImage(File image) {

    }

    @Override
    public void removeCoverImage() {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceLocationScreenImpl extends FXMLScreen
        implements TrekkingPlaceLocationScreen, Initializable {

    private TrekkingPlaceLocationPresenter _presenter;

    @Inject
    public TrekkingPlaceLocationScreenImpl(TrekkingPlaceLocationPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void showContinue() {

    }

    @Override
    public void hideContinue() {

    }

    @Override
    public void showMap() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void navigateToPhotos() {
        navigate(TrekkingPlacePhotosScreenImpl.class);
    }

    @FXML
    public void onNavigateToPhotos()
    {
        _presenter.requestToNavigateToPhotos();
    }
    @Override
    protected String fxmlPath() {
        return "/view/create_place/step2.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

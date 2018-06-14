package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceDetailsScreenImpl extends FXMLScreen
        implements PlaceDetailsScreen, Initializable {

    private final PlaceDetailsPresenter _presenter;

    @FXML
    private ImageView _ivPlaceImg;

    @FXML
    private Label _lblLikeCount;

    @FXML
    private Label _lblGoingCount;

    @FXML
    private Label _lblCheckedInCount;


    @Inject
    public PlaceDetailsScreenImpl(PlaceDetailsPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchPlaceDetails(_presenter.getPlaceId());
    }

    @Override
    public void updatePlaceInfo(PlaceDetails placeDetails) {
        Image image = new Image(placeDetails.getCoverImageUrl(), true);

        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _ivPlaceImg.setImage(image);
            }
        });

        _lblLikeCount.setText("+" + placeDetails.getLikes().size());
        _lblGoingCount.setText("+" + placeDetails.getNumberOfPeopleGoing());
    }

    @Override
    public void navigateToCreateTrekkingPlan() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void updateUserRating(int rate) {

    }

    @Override
    public void onLiked() {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    public void updateUserAvatar(String avatar) {

    }

    @Override
    protected String fxmlPath() {
        return "/view/home/place_item_details.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

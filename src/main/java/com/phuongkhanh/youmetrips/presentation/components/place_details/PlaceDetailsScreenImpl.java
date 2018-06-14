package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceDetailsScreenImpl extends FXMLScreen
        implements PlaceDetailsScreen, Initializable {

    private final Provider<CreatePlanWindow> _planWindow;
    private final PlaceDetailsPresenter _presenter;

    @FXML
    private ImageView _ivPlaceImg;

    @FXML
    private Label _lblLikeCount;

    @FXML
    private Label _lblGoingCount;

    @FXML
    private Label _lblCheckedInCount;

    @FXML
    private ImageView _imgMapView;

    @FXML
    private ProgressIndicator _progressIndicator;

    @FXML
    private ProgressIndicator _coverProgressIndicator;


    @Inject
    public PlaceDetailsScreenImpl(PlaceDetailsPresenter presenter, Provider<CreatePlanWindow> planWindow) {
        _presenter = presenter;
        _presenter.setView(this);
        _planWindow = planWindow;
        _presenter.fetchPlaceDetails(_presenter.getPlaceId());
    }

    @Override
    public void updatePlaceInfo(PlaceDetails placeDetails) {
        Image coverImage = new Image(placeDetails.getCoverImageUrl(), true);
        _coverProgressIndicator.setVisible(true);
        _coverProgressIndicator.setProgress(0);

        coverImage.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _ivPlaceImg.setImage(coverImage);
                _coverProgressIndicator.setVisible(false);
            } else {
                _coverProgressIndicator.setProgress(newValue.doubleValue());
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
        mapUrl = mapUrl.replaceAll(" ", "%20");
        Image mapImage = new Image(mapUrl, true);
        _progressIndicator.setVisible(true);

        mapImage.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _imgMapView.setImage(mapImage);
                _progressIndicator.setVisible(false);
            } else {
                _progressIndicator.setVisible(true);
                _progressIndicator.setProgress(newValue.doubleValue());
            }
        });
    }

    @Override
    public void updateUserAvatar(String avatar) {

    }

    @Override
    public void navigateToCreatePlan() {
        this.getWindow().close();
        CreatePlanWindow createPlanWindow = _planWindow.get();
        createPlanWindow.attach(new Stage());
        createPlanWindow.show();
    }

    @Override
    protected String fxmlPath() {
        return "/view/home/place_item_details.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onGoNow()
    {
        _presenter.requestToNavigateToCreatePlan();
    }
}

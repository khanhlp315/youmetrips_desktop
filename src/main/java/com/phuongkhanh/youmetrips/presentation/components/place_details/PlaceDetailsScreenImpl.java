package com.phuongkhanh.youmetrips.presentation.components.place_details;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.controls.RelevantPlanCell;
import com.phuongkhanh.youmetrips.presentation.controls.ReviewCell;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.api.models.Review;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

    @FXML
    private ListView _lvReviewItem;

    @FXML
    private Circle _cirAvatar;

    @FXML
    private Label _lblRating;

    @FXML
    private Label _lblTotalRating;

    @FXML
    private Rating _rating;

    @FXML
    private Label _lbl5Star;

    @FXML
    private Label _lbl4Star;

    @FXML
    private Label _lbl3Star;

    @FXML
    private Label _lbl2Star;

    @FXML
    private Label _lbl1Star;

    @FXML
    private JFXTextField _taComment;

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
        Image imageAva = new Image(avatar == null ||
                (avatar.equals("http://docker.youthdev.net:23010/static//77-img_20180405_190732-4f4356f8-f759-4c28-8a60-e9fef2c92920.jpg")) ||
                (avatar.equals("http://docker.youthdev.net:23010/static//81-img_20180323_203840_961-f8a0f475-94cc-4ba3-a690-76cc35823e12.jpg")) ? CommonUtils.getNeutralAvatar() : avatar, true);

        imageAva.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _cirAvatar.setFill(new ImagePattern(imageAva));
            }
        });
    }

    @Override
    public void navigateToCreatePlan() {
        this.getWindow().close();
        CreatePlanWindow createPlanWindow = _planWindow.get();
        createPlanWindow.attach(new Stage());
        createPlanWindow.show();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateReview(PlaceDetails placeDetails) {
        double totalRating = 0;
        int fiveStars = 0;
        int fourStars = 0;
        int threeStars = 0;
        int twoStars = 0;
        int oneStars = 0;

        for (Review review : placeDetails.getReviews()) {
            totalRating += review.getRate();
            switch (review.getRate()) {
                case 5: {
                    fiveStars++;
                    break;
                }
                case 4: {
                    fourStars++;
                    break;
                }
                case 3: {
                    threeStars++;
                    break;
                }
                case 2: {
                    twoStars++;
                    break;
                }
                case 1: {
                    oneStars++;
                    break;
                }
            }
        }
        _lbl1Star.setText(oneStars + "");
        _lbl2Star.setText(twoStars + "");
        _lbl3Star.setText(threeStars + "");
        _lbl4Star.setText(fourStars + "");
        _lbl5Star.setText(fiveStars + "");

        if (placeDetails.getReviews().size() != 0)
            _lblRating.setText(totalRating / placeDetails.getReviews().size() + "");
        else
            _lblRating.setText("0");

        _lblTotalRating.setText(placeDetails.getReviews().size() + "");

        _taComment.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("hi");
                if (event.getCode() == KeyCode.ENTER) {
                    if (_rating.getRating() == 0)
                        return;
                    _presenter.rate(_presenter.getPlaceId(), (int) _rating.getRating(), _taComment.getText());
                }
            }
        });

        _lvReviewItem.setItems(FXCollections.observableArrayList(placeDetails.getReviews().stream().map(ReviewCell::new).collect(Collectors.toList())));
    }

    @Override
    protected String fxmlPath() {
        return "/view/home/places/place_item_details.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onGoNow() {
        _presenter.requestToNavigateToCreatePlan();
    }
}

package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.CreatePlan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public class TrekkingPlanPreviewScreenImpl extends FXMLScreen
implements TrekkingPlanPreviewScreen, Initializable {

    private TrekkingPlanPreviewPresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private Rectangle _rectPlaceMap;

    @FXML
    private Rectangle _rectPlaceImage;

    @FXML
    private Label _lblFromDate;

    @FXML
    private Label _lblToDate;

    @FXML
    private Label _lblFromHowLong;

    @FXML
    private Label _lblToHowLong;

    @FXML
    private Label _lblStar;

    @Inject
    public TrekkingPlanPreviewScreenImpl(TrekkingPlanPreviewPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public Scene render() {
        _presenter.fetchPlan();
        return super.render();
    }

    @Override
    public void setLoading(boolean value) {
        _btnNext.setDisable(!value);
    }

    @Override
    public void updateMapUrl(String mapUrl) {
        mapUrl = mapUrl.replaceAll(" ","%20");
        Image image = new Image(mapUrl, true);

        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _rectPlaceMap.setFill(new ImagePattern(image));
            }
        });
    }

    @Override
    public void updatePlan(CreatePlan plan) {
        _lblFromDate.setText(plan.getWhenToGoMin());
        _lblToDate.setText(plan.getWhenToGoMax());
        _lblFromHowLong.setText(plan.getHowLongMin() == 1 ? plan.getHowLongMin() + " days" : plan.getHowLongMin() + " days");
        _lblToHowLong.setText(plan.getHowLongMax() + " days");
        _lblStar.setText(plan.getHotelLevel() + " ");
    }

    @Override
    public void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void closeWindow() {
        getWindow().close();
    }

    @Override
    public void updatePlaceImage(String coverImageUrl) {
        coverImageUrl = coverImageUrl.replaceAll(" ","%20");
        Image image = new Image(coverImageUrl, true);

        image.progressProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() == 1.0) {
                _rectPlaceImage.setFill(new ImagePattern(image));
            }
        });
    }

    @FXML
    public void onCreatePlan()
    {
        _presenter.createCreatePlan();
    }
    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step5.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

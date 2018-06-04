package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import com.phuongkhanh.youmetrips.services.api.utils.Constants;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class RelevantPlanCell extends ListCell<RelevantPlan> {

    @FXML
    private Label _lblFirstName;

    @FXML
    private Label _lblLastName;

    @FXML
    private Label _lblComments;

    @FXML
    private Label _lblStars;

    @FXML
    private Label _lblDateRange;

    @FXML
    private Label _lblTimeRange;

    @FXML
    private Label _lblOccupation;

    @FXML
    private Label _lblPlaceName;

    @FXML
    private ImageView _ivImageCover;

    @FXML
    private Circle _cirAvatar;

    @FXML
    private Circle _cirCountry;

    private Image _coverImage;
    private ChangeListener _coverImageListener;


    public RelevantPlanCell(){
        loadFXML();
    }

    private void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/plans/plan_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(RelevantPlan item, boolean empty) {
        super.updateItem(item, empty);

        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            _lblFirstName.setText(item.getUserFirstName());
            _lblLastName.setText(item.getUserLastName());
            _lblOccupation.setText(item.getUserOccupation());


            Image avaImage = new Image(item.getUserAvatarUrl() != null && !item.getUserAvatarUrl().equals("http://docker.youthdev.net:23010/static//77-img_20180405_190732-4f4356f8-f759-4c28-8a60-e9fef2c92920.jpg") ? item.getUserAvatarUrl() : CommonUtils.getNeutralAvatar(), true);
            Image flagImage = new Image(item.getUserNationalityFlagUrl() != null  ? item.getUserNationalityFlagUrl() : CommonUtils.getNeutralFlag(), true);
            //_cirAvatar.setFill(new ImagePattern(new Image(this.getClass().getClassLoader().getResource("images/vietnam.png").toString())));

            System.out.println(item.getUserAvatarUrl());
            if(item.getUserAvatarUrl() == null || item.getUserAvatarUrl().equals("null")){
                return;
            }
            avaImage.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1.0) {
                          _cirAvatar.setFill(new ImagePattern(avaImage));
                    }
                }
            });
            flagImage.progressProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1.0) {
                        _cirCountry.setFill(new ImagePattern(flagImage));
                    }
                }
            });

            if(_coverImage != null){
                _coverImage.progressProperty().removeListener(_coverImageListener);
            }
            _coverImage = new Image(item.getPlace().getCoverImageUrl(), true);
            _coverImageListener = new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if(newValue.doubleValue() == 1.0)
                        _ivImageCover.setImage(_coverImage);
                }
            };
            _coverImage.progressProperty().addListener(_coverImageListener);
            _lblPlaceName.setText(item.getPlace().getName());
            _lblStars.setText(String.valueOf(item.getHotelLevel()));
            String fromToDate = "";
            if(item.getWhenToGoMin().getYear() == item.getWhenToGoMax().getYear()){
                fromToDate = item.getWhenToGoMin().getDayOfMonth() + "/" + item.getWhenToGoMin().getMonthValue() + " - " +
                        item.getWhenToGoMax().getDayOfMonth() + "/" + item.getWhenToGoMax().getMonthValue() + "/" + item.getWhenToGoMax().getYear();
            }
            else {
                fromToDate = item.getWhenToGoMin().toString() +" - " + item.getWhenToGoMax().toString();
            }
            _lblDateRange.setText(fromToDate);
            _lblTimeRange.setText(item.getHowLongMin() + " - " + item.getHowLongMax() + " day(s)");
            _lblComments.setText(String.valueOf(item.getNumberOfComments()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

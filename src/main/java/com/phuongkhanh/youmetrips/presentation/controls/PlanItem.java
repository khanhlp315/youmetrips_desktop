package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;
import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralFlag;

public class PlanItem extends AnchorPane {

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
    private Image _imageCover;

    @FXML
    private Circle _cirAvatar;

    @FXML
    private Circle _cirCountry;

    private String _avatarUrl;
    private String _countryFlagUrl;

    private static FXMLLoader fxmlLoader = new FXMLLoader(PlanItem.class.getClassLoader().getResource(
            "view/home/plans/plan_item.fxml"));


    public PlanItem() {


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

    public PlanItem(String userFirstName, String userLastName, String userAvatarUrl,
                    String userOccupation, String userNationalityFlagUrl, String placeName,
                    int hotelStars, String dateRange, String timeRange, int numberOfComments) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/plans/plan_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Image imgAvatar = new Image(userAvatarUrl == null ? getNeutralAvatar() : userAvatarUrl);
        Image imgCountry = new Image(userNationalityFlagUrl == null ? getNeutralAvatar() : userNationalityFlagUrl);

        _lblFirstName.setText(userFirstName);
        _lblLastName.setText(userLastName);
        _lblOccupation.setText(userOccupation);
        _cirAvatar.setFill(new ImagePattern(imgAvatar));
        _cirCountry.setFill(new ImagePattern(imgCountry));

        _lblPlaceName.setText(placeName);
        _lblStars.setText(String.valueOf(hotelStars));
        _lblDateRange.setText(dateRange);
        _lblTimeRange.setText(timeRange);
        _lblComments.setText(String.valueOf(numberOfComments));

        _avatarUrl = userAvatarUrl;
        _countryFlagUrl = userNationalityFlagUrl;
    }

    void setUserFirstName(String value) {
        _lblFirstName.setText(value);
    }

    String getUserFirstName() {
        return _lblFirstName.getText();
    }

    void setUserLastName(String value) {
        _lblLastName.setText(value);
    }

    String getUserLastName() {
        return _lblLastName.getText();
    }

    void setOccupation(String value) {
        _lblOccupation.setText(value);
    }

    String getOccupation() {
        return _lblOccupation.getText();
    }

    void setUserAvatarUrl(String value) {
        _avatarUrl = value;
        _cirAvatar.setFill(new ImagePattern(new Image(value == null ? getNeutralAvatar() : value)));
    }

    String getUserAvatarUrl() {
        return _avatarUrl;
    }

    void setUserNationalityFlagUrl(String value) {
        _countryFlagUrl = value;
        _cirCountry.setFill(new ImagePattern(new Image(value == null ? getNeutralFlag() : value)));
    }

    void setPlaceName(String value) {
        _lblPlaceName.setText(value);
    }

    String getPlaceName() {
        return _lblPlaceName.getText();
    }

    void setHotelStars(int value) {
        _lblStars.setText(String.valueOf(value));
    }

    int getHotelStars() {
        return Integer.parseInt(_lblStars.getText());
    }

    void setDateRange(String value) {
        _lblDateRange.setText(value);
    }

    String getDateRange() {
        return _lblDateRange.getText();
    }

    void setTimeRange(String value) {
        _lblTimeRange.setText(value);
    }

    String getTimeRange() {
        return _lblTimeRange.getText();
    }
}

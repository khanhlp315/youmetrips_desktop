package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class UserPlanItem extends HBox {

    @FXML
    private Rectangle _rectPlaceImage;

    @FXML
    private Label _lblPlaceName;

    @FXML
    private Label _lblFromToDate;

    @FXML
    private Label _lblHowLong;

    private String _tempUrl = "";


    public UserPlanItem()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/profile/user_plan_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public UserPlanItem(String imageUrl, String placeName, String date, String longDate)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/profile/user_plan_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Image img = new Image(imageUrl);
        _rectPlaceImage.setFill(new ImagePattern(img));
        _tempUrl = imageUrl;

        _lblPlaceName.setText(placeName);
        _lblFromToDate.setText(date);
        _lblHowLong.setText(longDate);
    }

    public String getImageUrl()
    {
        return _tempUrl;
    }

    public void setImageUrl(String url)
    {
        Image img = new Image(url);
        _rectPlaceImage.setFill(new ImagePattern(img));
        _tempUrl = url;
    }

    public String getPlaceName()
    {
        return _lblPlaceName.getText();
    }

    public void setPlaceName(String placeName)
    {
        _lblPlaceName.setText(placeName);
    }

    public String getFromToDate()
    {
        return _lblFromToDate.getText();
    }

    public void setFromToDate(String date)
    {
        _lblFromToDate.setText(date);
    }

    public String getHowLong()
    {
        return _lblHowLong.getText();
    }

    public void setHowLong(String longTime)
    {
        _lblFromToDate.setText(longTime);
    }
}

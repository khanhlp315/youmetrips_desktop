package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlaceItem extends AnchorPane {
    @FXML
    private ImageView _ivPlaceImg;

    @FXML
    private Label _lblPlaceName;

    @FXML
    private Label _lblHashtag;

    @FXML
    private Label _lblPeopleCount;

    @FXML
    private Label _lblJob1;

    private int _peopleGoing = 0;
    private int _peopleLove = 0;
    private String _imgUrl = "";

    public PlaceItem(String imageUrl, String placeName, String hashTag, int peopleCount, int peopleLoveCount) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/places/place_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _ivPlaceImg.setImage(null);
        _ivPlaceImg.setImage(new Image(imageUrl));
        _lblPlaceName.setText(placeName);
        _lblHashtag.setText(hashTag);
        _lblPeopleCount.setText(String.valueOf(peopleCount));
        _lblJob1.setText(String.valueOf(peopleLoveCount));
        _imgUrl = imageUrl;
        _peopleGoing = peopleCount;
        _peopleLove = peopleLoveCount;
    }

    public String getImageUrl() {
        return _imgUrl;
    }

    public void setImageUrl(String imageUrl) {
        _ivPlaceImg.setImage(null);
        _ivPlaceImg.setImage(new Image(imageUrl));
        _imgUrl = imageUrl;
    }

    public String getPlaceName() {
        return _lblPlaceName.getText();
    }

    public void setPlaceName(String placeName) {
        _lblPlaceName.setText(placeName);
    }

    public String getHashTag() {
        return _lblHashtag.getText();
    }

    public void setHashTag(String hashTag) {
        _lblHashtag.setText(hashTag);
    }

    public int getPeopleGoing() {
        return _peopleGoing;
    }

    public void setPeopleCount(int peopleCount)
    {
        _lblPeopleCount.setText(String.valueOf(peopleCount));
        _peopleGoing = peopleCount;
    }

    public int getPeopleLoveCount()
    {
        return _peopleLove;
    }

    public void setPeopleLoveCount(int peopleLoveCount)
    {
        _lblJob1.setText(String.valueOf(peopleLoveCount));
        _peopleLove = peopleLoveCount;
    }
}

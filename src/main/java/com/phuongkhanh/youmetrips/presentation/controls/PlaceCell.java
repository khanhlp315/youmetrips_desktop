package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlaceCell extends ListCell<Place> {
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

    private Place _currentPlace;

    public PlaceCell() {
        loadFXML();

    }

    private void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/places/place_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(Place item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            if(_currentPlace == null || !item.equals(_currentPlace)){
                _currentPlace = item;
                //_ivPlaceImg.setImage(null);
                //_ivPlaceImg.setImage(new Image(imageUrl));
                _lblPlaceName.setText(item.getName());
                String allTags = "";
                for(String hashtag: item.getTags()){
                    allTags += "#" + hashtag +" ";
                }
                _lblHashtag.setText(allTags);
                _lblPeopleCount.setText(String.valueOf(item.getNumberOfPeopleGoing()));
                _lblJob1.setText(String.valueOf(item.getNumberOfLikes()));
            }

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

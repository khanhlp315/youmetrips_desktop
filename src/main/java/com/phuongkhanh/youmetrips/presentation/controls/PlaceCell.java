package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public class PlaceCell extends AnchorPane {

    /***************************************************************************
     *                                                                         *
     * Binding Properties from FXML                                                             *
     *                                                                         *
     **************************************************************************/
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

    @FXML
    private ImageView _ivHeartImage;



    /***************************************************************************
     *                                                                         *
     * Constructor                                                             *
     *                                                                         *
     **************************************************************************/
    public PlaceCell(Place item, EventHandler clickEvent) {
        loadFXML();
        this.setOnMouseClicked(clickEvent);
        Image image = new Image(item.getCoverImageUrl() != null ? item.getCoverImageUrl() : getNeutralAvatar(), true);
        image.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() == 1.0) {
                    _ivPlaceImg.setImage(image);
                    if (item.isLiked()) {
                        Image heartImage = new Image(this.getClass().getClassLoader().getResource("images/loved.png").toString());
                        _ivHeartImage.setImage(heartImage);
                    } else {
                        Image heartImage = new Image(this.getClass().getClassLoader().getResource("images/love.png").toString());
                        _ivHeartImage.setImage(heartImage);
                    }
                }
            }
        });
        _lblPlaceName.setText(item.getName());
        StringBuilder allTags = new StringBuilder();
        for (String hashtag : item.getTags())
            allTags.append("#").append(hashtag).append(" ");
        _lblHashtag.setText(allTags.toString());
        _lblPeopleCount.setText(String.valueOf(item.getNumberOfPeopleGoing()));
        _lblJob1.setText(String.valueOf(item.getNumberOfLikes()));

    }


    /***************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    private void loadFXML() {
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
}

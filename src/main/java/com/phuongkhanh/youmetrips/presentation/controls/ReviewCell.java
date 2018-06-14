package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.PlaceDetails;
import com.phuongkhanh.youmetrips.services.api.models.Review;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class ReviewCell  extends HBox {

    @FXML
    private Circle _cirAvatar;
    
    @FXML
    private Label _lblRating;
    
    @FXML
    private Label _lblFirstname;
    
    @FXML
    private Label _lblLastname;
    
    @FXML
    private Label _lblTime;
    
    @FXML
    private Label _lblComment;
    
    
    public ReviewCell(Review review)
    {
        loadFXML();
        Image avaImage = new Image(review.getReviewerAvatarUrl() == null ||
                (review.getReviewerAvatarUrl().equals("http://docker.youthdev.net:23010/static//77-img_20180405_190732-4f4356f8-f759-4c28-8a60-e9fef2c92920.jpg")) ||
                (review.getReviewerAvatarUrl().equals("http://docker.youthdev.net:23010/static//81-img_20180323_203840_961-f8a0f475-94cc-4ba3-a690-76cc35823e12.jpg")) ? CommonUtils.getNeutralAvatar(): review.getReviewerAvatarUrl(), true);
        
        avaImage.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() == 1.0) {
                    _cirAvatar.setFill(new ImagePattern(avaImage));
                }
            }
        });

        _lblRating.setText(review.getRate()+"");
        _lblFirstname.setText(review.getReviewerFirstName());
        _lblLastname.setText(review.getReviewerLastName());
        _lblTime.setText(review.getReviewedTime().toString());
        _lblComment.setText(review.getMessage());
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/places/review_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

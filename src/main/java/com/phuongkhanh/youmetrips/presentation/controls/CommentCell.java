package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Comment;
import com.phuongkhanh.youmetrips.utils.CommonUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class CommentCell extends AnchorPane {

    @FXML
    private Circle _circleAvatar;

    @FXML
    private Text _txtUserFirstName;

    @FXML
    private Text _txtUserLastName;

    @FXML
    private Text _txtTime;

    @FXML
    private Text _txtContent;


    public CommentCell(Comment comment) {
        loadFXML();

        _txtUserFirstName.setText(comment.getUserFirstName());
        _txtUserLastName.setText(comment.getUserLastName());
        _txtContent.setText(comment.getComment());
        _txtTime.setText(comment.getTime().toString());

        Image avaImage = new Image(comment.getUserAvatarUrl() == null ||
                (comment.getUserAvatarUrl().equals("http://docker.youthdev.net:23010/static//77-img_20180405_190732-4f4356f8-f759-4c28-8a60-e9fef2c92920.jpg")) ||
                (comment.getUserAvatarUrl().equals("http://docker.youthdev.net:23010/static//81-img_20180323_203840_961-f8a0f475-94cc-4ba3-a690-76cc35823e12.jpg")) ? CommonUtils.getNeutralAvatar() : comment.getUserAvatarUrl(), true);
        avaImage.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() == 1.0) {
                    _circleAvatar.setFill(new ImagePattern(avaImage));
                }
            }
        });
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/comment_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

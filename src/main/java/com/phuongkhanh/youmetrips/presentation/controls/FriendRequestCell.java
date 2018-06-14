package com.phuongkhanh.youmetrips.presentation.controls;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.services.api.models.FriendRequest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public class FriendRequestCell extends AnchorPane {
    @FXML
    private Rectangle _rectAvatar;

    @FXML
    private Label _lblFirstName;

    @FXML
    private Label _lblLastName;

    @FXML
    private JFXButton _btnAccept;

    @FXML
    private JFXButton _btnDelete;

    public FriendRequestCell(FriendRequest item, EventHandler<ActionEvent> onAccept, EventHandler<ActionEvent> onDecline){
        loadFXML();
        Image image = new Image(item.getUserAvatarUrl() != null ? item.getUserAvatarUrl() : getNeutralAvatar(), true);
        image.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.doubleValue() == 1.0) {
                    _rectAvatar.setFill(new ImagePattern(image));
                }
            }
        });
        _lblFirstName.setText(item.getUserFirstName());
        _lblLastName.setText(item.getUserLastName());
        _btnAccept.setOnAction(onAccept);
        _btnDelete.setOnAction(onDecline);
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/friend_requests/friend_request_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

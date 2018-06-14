package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Friend;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public final class FriendCell extends VBox {

    @FXML
    private Rectangle _rectAvatar;

    @FXML
    private Label _lblName;

    @FXML
    private ProgressIndicator _avatarProgressIndicator;

    public FriendCell(Friend item){
        loadFXML();
        Image image = new Image(item.getUserAvatarUrl() == null? getNeutralAvatar(): item.getUserAvatarUrl(), true);
        image.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(newValue.doubleValue() == 1.0){
                    _avatarProgressIndicator.setVisible(false);
                    _rectAvatar.setFill(new ImagePattern(image));
                }
                else {
                    _avatarProgressIndicator.setVisible(true);
                    _avatarProgressIndicator.setProgress(newValue.doubleValue());
                }
            }
        });
        _lblName.setText(item.getUserFirstName() + " " + item.getUserLastName());
    }

    private void loadFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/profile/friend_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}
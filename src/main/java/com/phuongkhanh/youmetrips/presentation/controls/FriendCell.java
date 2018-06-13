package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Friend;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public final class FriendCell extends ListCell<Friend> {

    @FXML
    private Rectangle _rectAvatar;

    @FXML
    private Label _lblName;

    @FXML
    private ProgressIndicator _avatarProgressIndicator;

    private Friend _currentItem;

    public FriendCell() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/profile/friend_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    protected void updateItem(Friend item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            if (_currentItem == null || !item.equals(_currentItem)) {
                _currentItem = item;
                Image image = new Image(item.getUserAvatarUrl() == null ? getNeutralAvatar() : item.getUserAvatarUrl(), true);
                image.progressProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        if (newValue.doubleValue() == 1.0) {
                            _avatarProgressIndicator.setVisible(false);
                            _rectAvatar.setFill(new ImagePattern(image));
                        } else {
                            _avatarProgressIndicator.setVisible(true);
                            _avatarProgressIndicator.setProgress(newValue.doubleValue());
                        }
                    }
                });
                _lblName.setText(item.getUserFirstName() + " " + item.getUserLastName());
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
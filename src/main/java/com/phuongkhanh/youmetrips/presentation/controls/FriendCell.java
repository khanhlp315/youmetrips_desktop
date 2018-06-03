package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.Friend;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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

    private Friend _currentItem;

    public FriendCell(){
        loadFXML();
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

    @Override
    protected void updateItem(Friend item, boolean empty) {
        super.updateItem(item, empty);

        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            if(_currentItem == null || !item.equals(_currentItem)){
                _currentItem = item;
                Task<Image> task = new Task<Image>() {
                    @Override
                    protected Image call() {
                        Image image = new Image(item.getUserAvatarUrl() == null? getNeutralAvatar(): item.getUserAvatarUrl(), true);

                        while(!(image.getProgress() == 1.0)){
                        }
                        return image;
                    }
                };
                task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event->{
                    _rectAvatar.setFill(new ImagePattern(task.getValue()));
                });
                //new Thread(task).start();
                _lblName.setText(item.getUserFirstName() + " " + item.getUserLastName());
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
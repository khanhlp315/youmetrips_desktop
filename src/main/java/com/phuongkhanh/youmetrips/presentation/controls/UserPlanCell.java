package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.UserTrekkingPlan;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class UserPlanCell extends ListCell<UserTrekkingPlan> {
    @FXML
    private Rectangle _rectPlaceImage;

    @FXML
    private Label _lblPlaceName;

    @FXML
    private Label _lblFromToDate;

    @FXML
    private Label _lblHowLong;

    public UserPlanCell()
    {
        loadFXML();
    }

    private void loadFXML(){
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

    @Override
    protected void updateItem(UserTrekkingPlan item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {
            Task<Image> task = new Task<Image>() {
                @Override
                protected Image call() {
                    Image image = new Image(item.getPlace().getCoverImageUrl(), true);

                    while(!(image.getProgress() == 1.0)){
                    }
                    return image;
                }
            };
            task.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, event->{
                _rectPlaceImage.setFill(new ImagePattern(task.getValue()));
            });
            //new Thread(task).start();
            _lblPlaceName.setText(item.getPlace().getName());
            String fromToDate = "";
            if(item.getWhenToGoMin().getYear() == item.getWhenToGoMax().getYear()){
                fromToDate = item.getWhenToGoMin().getDayOfMonth() + "/" + item.getWhenToGoMin().getMonthValue() + " - " +
                        item.getWhenToGoMax().getDayOfMonth() + "/" + item.getWhenToGoMax().getMonthValue() + "/" + item.getWhenToGoMax().getYear();
            }
            else {
                fromToDate = item.getWhenToGoMin().toString() +" - " + item.getWhenToGoMax().toString();
            }
            _lblFromToDate.setText(fromToDate);
            _lblHowLong.setText(item.getHowLongMin() + " - " + item.getHowLongMax() + " day(s)");
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        }
    }
}

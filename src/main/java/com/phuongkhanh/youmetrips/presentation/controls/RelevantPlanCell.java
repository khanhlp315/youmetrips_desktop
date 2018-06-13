package com.phuongkhanh.youmetrips.presentation.controls;

import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

import java.io.IOException;

public class RelevantPlanCell extends ListCell<RelevantPlan> {

    @FXML
    private Label _lblFirstName;

    @FXML
    private Label _lblLastName;

    @FXML
    private Label _lblComments;

    @FXML
    private Label _lblStars;

    @FXML
    private Label _lblDateRange;

    @FXML
    private Label _lblTimeRange;

    @FXML
    private Label _lblOccupation;

    @FXML
    private Label _lblPlaceName;

    @FXML
    private Image _imageCover;

    @FXML
    private Circle _cirAvatar;

    @FXML
    private Circle _cirCountry;


    public RelevantPlanCell() {
        loadFXML();
    }

    private void loadFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/plans/plan_item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void updateItem(RelevantPlan item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        } else {
            _lblFirstName.setText(item.getUserFirstName());
            _lblLastName.setText(item.getUserLastName());
            _lblOccupation.setText(item.getUserOccupation());
            //_cirAvatar.setFill(new ImagePattern(new Image(userAvatarUrl)));
            //_cirCountry.setFill(new ImagePattern(new Image(userNationalityFlagUrl == null? getNeutralFlag())));
            _lblPlaceName.setText(item.getPlace().getName());
            _lblStars.setText(String.valueOf(item.getHotelLevel()));
            String fromToDate = "";
            if (item.getWhenToGoMin().getYear() == item.getWhenToGoMax().getYear()) {
                fromToDate = item.getWhenToGoMin().getDayOfMonth() + "/" + item.getWhenToGoMin().getMonthValue() + " - " +
                        item.getWhenToGoMax().getDayOfMonth() + "/" + item.getWhenToGoMax().getMonthValue() + "/" + item.getWhenToGoMax().getYear();
            } else {
                fromToDate = item.getWhenToGoMin().toString() + " - " + item.getWhenToGoMax().toString();
            }
            _lblDateRange.setText(fromToDate);
            _lblTimeRange.setText(item.getHowLongMin() + " - " + item.getHowLongMax() + " day(s)");
            _lblComments.setText(String.valueOf(item.getNumberOfComments()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

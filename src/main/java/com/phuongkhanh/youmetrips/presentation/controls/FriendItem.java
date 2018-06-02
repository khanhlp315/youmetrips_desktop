package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

import static com.phuongkhanh.youmetrips.utils.CommonUtils.getNeutralAvatar;

public class FriendItem extends VBox {

    @FXML
    private Rectangle _rectAvatar;

    @FXML
    private Label _lblName;

    private String tempUrl;

    public FriendItem(String imageUrl, String firstName, String lastName)
    {
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

        Image img = new Image(imageUrl == null ? getNeutralAvatar(): imageUrl);
        _rectAvatar.setFill(new ImagePattern(img));
        tempUrl = imageUrl;
        _lblName.setText(firstName + " " + lastName);
    }

    public String getImageUrl()
    {
        return tempUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        Image img = new Image(imageUrl == null ? getNeutralAvatar(): imageUrl);
        _rectAvatar.setFill(new ImagePattern(img));
        tempUrl = imageUrl;
    }

    public String getName()
    {
        return _lblName.getText();
    }

    public void setName(String firstName, String lastName)
    {
        _lblName.setText(firstName + " " + lastName);
    }
}


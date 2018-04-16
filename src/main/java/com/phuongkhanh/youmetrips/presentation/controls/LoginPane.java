package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


/*
 * @author by LeVoGiaKhang
 */
public class LoginPane extends AnchorPane
{
    @FXML
    private AnchorPane _anchor;

    private Node _node;
    public LoginPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../../../../../../resources/custom_controls/login_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public LoginPane(Node ...loginNodes) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "../../../../../../resources/custom_controls/login_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _anchor.getChildren().addAll(loginNodes);
    }
}

package com.phuongkhanh.youmetrips.presentation.controls;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HomePane extends VBox {

    @FXML
    private AnchorPane _anchor;

    @FXML
    private JFXButton _profileButton;

    public HomePane()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/main_window.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public HomePane(Node...loginNodes) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/sign_in/login_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _anchor.getChildren().addAll(loginNodes);
    }

    public void setLoginNodes(Node ...loginNodes){
        _anchor.getChildren().clear();
        _anchor.getChildren().addAll(loginNodes);
    }

    public ObservableList<Node> getLoginNodes(){
        return _anchor.getChildren();
    }

    public final EventHandler<? super MouseEvent> getOnProfileClicked() {
        return _profileButton.getOnMouseClicked();
    }

    public final void setOnProfileClicked(
            EventHandler<? super MouseEvent> value) {
        _profileButton.setOnMouseClicked(value);
    }


}
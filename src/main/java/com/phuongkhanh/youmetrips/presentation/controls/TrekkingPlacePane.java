package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrekkingPlacePane extends AnchorPane
{
    @FXML
    private VBox _vBox;

    public TrekkingPlacePane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/create_place/step_main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public TrekkingPlacePane(Node...trekkingPlaceNodes) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/create_place/step_main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _vBox.getChildren().addAll(trekkingPlaceNodes);
    }

    public void setTrekkingPlaceNodes(Node ...trekkingPlaceNodes){
        _vBox.getChildren().clear();
        _vBox.getChildren().addAll(trekkingPlaceNodes);
    }

    public ObservableList<Node> getTrekkingPlaceNodes(){
        return _vBox.getChildren();
    }
}
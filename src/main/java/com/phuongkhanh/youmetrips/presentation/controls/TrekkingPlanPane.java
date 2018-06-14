package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TrekkingPlanPane extends AnchorPane
{
    @FXML
    private VBox _vBox;

    public TrekkingPlanPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/create_plan/step_main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    public TrekkingPlanPane(Node...trekkingPlanNodes) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/create_plan/step_main.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _vBox.getChildren().addAll(trekkingPlanNodes);
    }

    public void setTrekkingPlanNodes(Node ...trekkingPlanNodes){
        _vBox.getChildren().clear();
        _vBox.getChildren().addAll(trekkingPlanNodes);
    }

    public ObservableList<Node> getTrekkingPlanNodes(){
        return _vBox.getChildren();
    }
}
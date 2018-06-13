package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class TrekkingPlanPane extends AnchorPane
{
    @FXML
    private VBox _vBox;

    @FXML
    private Label _lbl1;
    @FXML
    private Label _lbl2;
    @FXML
    private Label _lbl3;
    @FXML
    private Label _lbl4;
    @FXML
    private Label _lbl5;

    @FXML
    private Circle _circle1;
    @FXML
    private Circle _circle2;
    @FXML
    private Circle _circle3;
    @FXML
    private Circle _circle4;
    @FXML
    private Circle _circle5;

    @FXML
    private Text _txt1;
    @FXML
    private Text _txt2;
    @FXML
    private Text _txt3;
    @FXML
    private Text _txt4;
    @FXML
    private Text _txt5;


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

    public void setStep(int step){
        _lbl1.setVisible(false);
        _txt1.setFill(javafx.scene.paint.Color.web("a6a9ae"));
        _circle1.setStroke(javafx.scene.paint.Color.web("#e4e5e7"));
        _circle1.setFill(javafx.scene.paint.Color.web("#e4e5e7"));
        _lbl2.setVisible(false);
        _txt2.setFill(javafx.scene.paint.Color.web("a6a9ae"));
        _circle2.setStroke(javafx.scene.paint.Color.web("#e4e5e7"));
        _circle2.setFill(javafx.scene.paint.Color.web("#e4e5e7"));
        _lbl3.setVisible(false);
        _txt3.setFill(javafx.scene.paint.Color.web("a6a9ae"));
        _circle3.setStroke(javafx.scene.paint.Color.web("#e4e5e7"));
        _circle3.setFill(javafx.scene.paint.Color.web("#e4e5e7"));
        _lbl4.setVisible(false);
        _txt4.setFill(javafx.scene.paint.Color.web("a6a9ae"));
        _circle4.setStroke(javafx.scene.paint.Color.web("#e4e5e7"));
        _circle4.setFill(javafx.scene.paint.Color.web("#e4e5e7"));
        _lbl5.setVisible(false);
        _txt5.setFill(javafx.scene.paint.Color.web("a6a9ae"));
        _circle5.setStroke(javafx.scene.paint.Color.web("#e4e5e7"));
        _circle5.setFill(javafx.scene.paint.Color.web("#e4e5e7"));

        if(step >= 1){
            _lbl1.setVisible(true);
            _txt1.setFill(javafx.scene.paint.Color.WHITE);
            _circle1.setStroke(javafx.scene.paint.Color.web("#1a535c"));
            _circle1.setFill(javafx.scene.paint.Color.web("#1a535c"));
        }
        if(step >= 2){
            _lbl2.setVisible(true);
            _txt2.setFill(javafx.scene.paint.Color.WHITE);
            _circle2.setStroke(javafx.scene.paint.Color.web("#1a535c"));
            _circle2.setFill(javafx.scene.paint.Color.web("#1a535c"));
        }
        if(step >= 3){
            _lbl3.setVisible(true);
            _txt3.setFill(javafx.scene.paint.Color.WHITE);
            _circle3.setStroke(javafx.scene.paint.Color.web("#1a535c"));
            _circle3.setFill(javafx.scene.paint.Color.web("#1a535c"));
        }
        if(step >= 4){
            _lbl4.setVisible(true);
            _txt4.setFill(javafx.scene.paint.Color.WHITE);
            _circle4.setStroke(javafx.scene.paint.Color.web("#1a535c"));
            _circle4.setFill(javafx.scene.paint.Color.web("#1a535c"));
        }
        if(step >= 5){
            _lbl5.setVisible(true);
            _txt5.setFill(javafx.scene.paint.Color.WHITE);
            _circle5.setStroke(javafx.scene.paint.Color.web("#1a535c"));
            _circle5.setFill(javafx.scene.paint.Color.web("#1a535c"));
        }
    }

    public int getStep(){
        return 1;
    }

    public ObservableList<Node> getTrekkingPlanNodes(){
        return _vBox.getChildren();
    }
}
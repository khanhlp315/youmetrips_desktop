package com.phuongkhanh.youmetrips.presentation.controls;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Drawer extends JFXDrawer {


    @FXML
    private JFXButton _btnProfile;

    @FXML
    private JFXButton _btnPlans;

    @FXML
    private JFXButton _btnPlaces;

    @FXML
    private JFXButton _btnFriendRequest;

    @FXML
    private JFXButton _btnBack;

    @FXML
    private JFXButton _btnCreatePlace;

    @FXML
    private JFXButton _btnCreatePlan;

    @FXML
    private JFXButton _btnEditProfile;

    public Drawer()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/drawer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public final EventHandler<? super MouseEvent> getOnProfileClicked() {
        return _btnProfile.getOnMouseClicked();
    }

    public final void setOnProfileClicked(
            EventHandler<? super MouseEvent> value) {
        _btnProfile.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnPlanClicked() {
        return _btnPlans.getOnMouseClicked();
    }

    public final void setOnPlanClicked(
            EventHandler<? super MouseEvent> value) {
        _btnPlans.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnPlaceClicked() {
        return _btnPlaces.getOnMouseClicked();
    }

    public final void setOnPlaceClicked(
            EventHandler<? super MouseEvent> value) {
        _btnPlaces.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnFriendRequestClicked() {
        return _btnFriendRequest.getOnMouseClicked();
    }

    public final void setOnFriendRequestClicked(
            EventHandler<? super MouseEvent> value) {
        _btnFriendRequest.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnBackClicked() {
        return _btnBack.getOnMouseClicked();
    }

    public final void setOnBackClicked(
            EventHandler<? super MouseEvent> value) {
        _btnBack.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnCreatePlaceClicked() {
        return _btnCreatePlace.getOnMouseClicked();
    }

    public final void setOnCreatePlaceClicked(
            EventHandler<? super MouseEvent> value) {
        _btnCreatePlace.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnCreatePlanClicked() {
        return _btnCreatePlan.getOnMouseClicked();
    }

    public final void setOnCreatePlanClicked(
            EventHandler<? super MouseEvent> value) {
        _btnCreatePlan.setOnMouseClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnEditProfileClicked() {
        return _btnEditProfile.getOnMouseClicked();
    }

    public final void setOnEditProfileClicked(
            EventHandler<? super MouseEvent> value) {
        _btnEditProfile.setOnMouseClicked(value);
    }
}

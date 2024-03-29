package com.phuongkhanh.youmetrips.presentation.controls;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HomePane extends GridPane {

    @FXML
    private ScrollPane _scrollPane;

    @FXML
    private HomeDrawer _drawer;


    public HomePane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/home/home_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public HomePane(Node child) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(
                "view/sign_in/login_pane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        _scrollPane.setContent(child);
    }

    public Node getHomeNode() {
        return _scrollPane.getContent();
    }

    public void setHomeNode(Node child) {
        _scrollPane.setContent(child);
    }

    public final EventHandler<? super MouseEvent> getOnProfileClicked() {
        return _drawer.getOnProfileClicked();
    }

    public final void setOnProfileClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnProfileClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnPlanClicked() {
        return _drawer.getOnPlanClicked();
    }

    public final void setOnPlanClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnPlanClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnPlaceClicked() {
        return _drawer.getOnPlaceClicked();
    }

    public final void setOnPlaceClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnPlaceClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnFriendRequestClicked() {
        return _drawer.getOnFriendRequestClicked();
    }

    public final void setOnFriendRequestClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnFriendRequestClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnRefreshClicked() {
        return _drawer.getOnRefreshClicked();
    }

    public final void setOnRefreshClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnRefreshClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnCreatePlaceClicked() {
        return _drawer.getOnCreatePlaceClicked();
    }

    public final void setOnCreatePlaceClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnCreatePlaceClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnCreatePlanClicked() {
        return _drawer.getOnCreatePlanClicked();
    }

    public final void setOnCreatePlanClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnCreatePlanClicked(value);
    }

    public final EventHandler<? super MouseEvent> getOnEditProfileClicked() {
        return _drawer.getOnEditProfileClicked();
    }

    public final void setOnEditProfileClicked(
            EventHandler<? super MouseEvent> value) {
        _drawer.setOnEditProfileClicked(value);
    }
}
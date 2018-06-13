package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceHashtagsScreenImpl extends FXMLScreen
implements TrekkingPlaceHashtagsScreen, Initializable{

    private TrekkingPlaceHashtagsPresenter _presenter;

    @FXML
    private JFXListView _lsView;

    @FXML
    private JFXTextField _tfPlace;

    @Inject
    public TrekkingPlaceHashtagsScreenImpl(TrekkingPlaceHashtagsPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step4.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _tfPlace.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    _presenter.requestAddHashtag(_tfPlace.getText());
                }
            }
        });
    }

    @Override
    public void showContinue() {

    }

    @Override
    public void hideContinue() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void addHashtag(String text) {
        _lsView.getItems().add(_tfPlace.getText());

    }

    @Override
    public void removeHashtag(String hashtag) {

    }
}

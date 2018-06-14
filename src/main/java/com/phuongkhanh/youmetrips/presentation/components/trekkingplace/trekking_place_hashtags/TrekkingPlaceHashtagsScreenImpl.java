package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import com.jfoenix.controls.JFXTextField;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrekkingPlaceHashtagsScreenImpl extends FXMLScreen
        implements TrekkingPlaceHashtagsScreen, Initializable {

    private TrekkingPlaceHashtagsPresenter _presenter;
    private List<String> _listHashtags;

    @FXML
    private ListView _lsView;

    @FXML
    private JFXTextField _tfPlace;

    @FXML
    private JFXButton _btnNext;

    @Inject
    public TrekkingPlaceHashtagsScreenImpl(TrekkingPlaceHashtagsPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step4.fxml";
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _listHashtags = new ArrayList<>();
        _tfPlace.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    _presenter.requestAddHashtag(_tfPlace.getText());
                    _tfPlace.setText("");
                }
            }
        });
        _lsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                // DO NOT REMOVE THIS
            }
        });
    }

    @Override
    public void showContinue() {
        _btnNext.setDisable(false);
    }

    @Override
    public void hideContinue() {
        _btnNext.setDisable(true);
    }

    @Override
    public void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void returnPlace(int placeId) {

    }

    @Override
    public void setLoading(boolean value) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void addHashtag(String text) {
        _listHashtags.add(text);
        _lsView.getItems().add("#" + text);
        _presenter.onInputUpdated(_listHashtags);

        this.getWindow().getStage().getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    _presenter.requestRemoveHashtag((String)_lsView.getSelectionModel().getSelectedItem());
                }
            }
        });

    }

    @SuppressWarnings("unchecked")
    @Override
    public void removeHashtag(String hashtag) {
        if(hashtag.contains("#")){
            hashtag = hashtag.replace("#", "");
        }
        _listHashtags.remove(hashtag);
        _lsView.getItems().clear();
        for(String tag: _listHashtags)
            _lsView.getItems().add("#" + tag);
        _presenter.onInputUpdated(_listHashtags);
    }

    @FXML
    public void onFinish() {
        _presenter.requestCreatePlace(_listHashtags);
    }
}

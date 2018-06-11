package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceHashtagsScreenImpl extends FXMLScreen
implements TrekkingPlaceHashtagsScreen, Initializable{

    @Inject
    public TrekkingPlaceHashtagsScreenImpl(TrekkingPlaceHashtagsPresenter presenter)
    {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step4.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    }

    @Override
    public void removeHashtag(String hashtag) {

    }
}

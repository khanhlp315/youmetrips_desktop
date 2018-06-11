package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceNameScreenImpl extends FXMLScreen
implements TrekkingPlaceNameScreen, Initializable {

    @Inject
    public TrekkingPlaceNameScreenImpl(TrekkingPlaceNamePresenter presenter)
    {

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
    protected String fxmlPath() {
        return "/view/create_place/step1.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

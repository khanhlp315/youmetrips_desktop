package com.phuongkhanh.youmetrips.presentation.components.trekkingplace;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlaceScreenImpl extends FXMLScreen
        implements TrekkingPlaceScreen, Initializable {

    @Inject
    public TrekkingPlaceScreenImpl(TrekkingPlacePresenter presenter) {

    }

    @Override
    public void returnPlace(int placeId) {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void setLoading(boolean value) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

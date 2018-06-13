package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrekkingPlanPlaceScreenImpl extends FXMLScreen
        implements TrekkingPlanPlaceScreen, Initializable {

    @Inject
    public TrekkingPlanPlaceScreenImpl(TrekkingPlanPlacePresenter presenter) {

    }

    @Override
    public void showContinue() {

    }

    @Override
    public void hideContinue() {

    }

    @Override
    public void updatePlaces(List<Place> places) {

    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

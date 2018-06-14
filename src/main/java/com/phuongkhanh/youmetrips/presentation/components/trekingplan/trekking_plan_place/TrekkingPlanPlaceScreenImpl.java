package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrekkingPlanPlaceScreenImpl extends FXMLScreen
        implements TrekkingPlanPlaceScreen, Initializable {

    private TrekkingPlanPlacePresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @Inject
    public TrekkingPlanPlaceScreenImpl(TrekkingPlanPlacePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void showContinue() {
        _btnNext.setDisable(true);
    }

    @Override
    public void hideContinue() {
        _btnNext.setDisable(false);
    }

    @Override
    public void updatePlaces(List<Place> places) {
        // Hiện ra list cho user chọn
    }

    @Override
    public void navigateToCreateTrekkingPlace() {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    public void navigateToTime() {
        navigate(TrekkingPlanTimeScreenImpl.class);
    }

    @FXML
    public void onNavigateToTime() {
        _presenter.requestNavigateToTime();
    }
    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step1.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

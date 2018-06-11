package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.presentation.windows.CreatePlanWindow;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Provider;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrekkingPlanPlaceScreenImpl extends FXMLScreen
implements TrekkingPlanPlaceScreen, Initializable {

    private TrekkingPlanPlacePresenter _presenter;

    @Inject
    public TrekkingPlanPlaceScreenImpl(TrekkingPlanPlacePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
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
    public void navigateToTime() {
        navigate(TrekkingPlanTimeScreenImpl.class);
    }

    @FXML
    public void onNavigateToTime()
    {
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

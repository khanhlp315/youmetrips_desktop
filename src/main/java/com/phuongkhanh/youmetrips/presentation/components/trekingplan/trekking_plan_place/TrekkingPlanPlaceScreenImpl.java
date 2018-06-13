package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time.TrekkingPlanTimeScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    @FXML
    private JFXComboBox _cbChoosePlace;

    @Inject
    public TrekkingPlanPlaceScreenImpl(TrekkingPlanPlacePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchPlaces();
        _cbChoosePlace.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                _presenter.onSelectedPlaceUpdated((Place)newValue);
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
    public void updatePlaces(List<Place> places) {
        _cbChoosePlace.setItems(FXCollections.observableArrayList(places));
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

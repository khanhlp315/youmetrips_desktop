package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TrekkingPlanTimeScreenImpl extends FXMLScreen
        implements TrekkingPlanTimeScreen, Initializable {
    private TrekkingPlanTimePresenter _presenter;

    @FXML
    private JFXDatePicker _dpFrom;

    @FXML
    private JFXDatePicker _dpTo;

    @FXML
    private JFXButton _btnNext;

    @Inject
    public TrekkingPlanTimeScreenImpl(TrekkingPlanTimePresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {
        _btnNext.setDisable(value);
    }

    @Override
    public void navigateToStay() {
        navigate(TrekkingPlanStayScreenImpl.class);
    }

    @FXML
    public void onNavigateToStay() {
        _presenter.requestToNavigateToStay();
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step2.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _dpFrom.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                _presenter.onInputUpdated(_dpFrom.getValue().toString(), _dpTo.getValue().toString());
            }
        });

        _dpTo.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                _presenter.onInputUpdated(_dpFrom.getValue().toString(), _dpTo.getValue().toString());
            }
        });
    }
}

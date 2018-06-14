package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanStayScreenImpl extends FXMLScreen
implements TrekkingPlanStayScreen, Initializable {

    private TrekkingPlanStayPresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private JFXComboBox _cbFrom;

    @FXML
    private JFXComboBox _cbTo;

    @Inject
    public TrekkingPlanStayScreenImpl(TrekkingPlanStayPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {
        _btnNext.setDisable(value);
    }

    @Override
    public void navigateToHotel() {
        navigate(TrekkingPlanHotelScreenImpl.class);
    }

    @FXML
    public void onNavigateToHotel()
    {
        _presenter.requestToNavigateToHotel();
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step3.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _cbFrom.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                _presenter.onInputUpdated(Integer.valueOf((String)_cbFrom.getValue()), Integer.valueOf((String)_cbTo.getValue()));
            }
        });

        _cbTo.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                _presenter.onInputUpdated(Integer.valueOf((String)_cbFrom.getValue()), Integer.valueOf((String)_cbTo.getValue()));
            }
        });
    }
}

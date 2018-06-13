package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public TrekkingPlanStayScreenImpl(TrekkingPlanStayPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        this.init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        ObservableList<String> options =
                FXCollections.observableArrayList();

        for (int i = 0; i <= 30 - 1; i++) {
            options.add(i + 1 + (i == 0 ? " day" : " days"));
        }

        _cbFrom.setItems(options);
        _cbTo.setItems(options);
    }

    @Override
    public void setCanNext(boolean value) {
        _btnNext.setDisable(!value);
    }

    @Override
    public void navigateToHotel() {
        navigate(TrekkingPlanHotelScreenImpl.class);
    }

    @FXML
    public void onNavigateToHotel() {
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
                Pattern p = Pattern.compile("-?\\d+");

                String from = (String) _cbFrom.getValue();
                Matcher mFrom = null;
                if (_cbFrom.getValue() != null) {
                    mFrom = p.matcher(from);
                    mFrom.find();
                }

                String to = ((String) _cbTo.getValue());
                Matcher mTo = null;
                if (_cbTo.getValue() != null) {
                    mTo = p.matcher(to);
                    mTo.find();
                }

                _presenter.onInputUpdated(_cbFrom.getValue() == null ? -1 : Integer.valueOf((String) mFrom.group()), _cbTo.getValue() == null ? -1 : Integer.valueOf((String) mTo.group()));
            }
        });

        _cbTo.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Pattern p = Pattern.compile("-?\\d+");

                String from = (String) _cbFrom.getValue();
                Matcher mFrom = null;
                if (_cbFrom.getId() != null) {
                    mFrom = p.matcher(from);
                    mFrom.find();
                }

                String to = (String) _cbTo.getValue();
                Matcher mTo = null;
                if (_cbTo.getValue() != null) {
                    mTo = p.matcher(to);
                    mTo.find();
                }

                _presenter.onInputUpdated(_cbFrom.getValue() == null ? -1 : Integer.valueOf((String) mFrom.group()), _cbTo.getValue() == null ? -1 : Integer.valueOf((String) mTo.group()));
            }
        });
    }
}

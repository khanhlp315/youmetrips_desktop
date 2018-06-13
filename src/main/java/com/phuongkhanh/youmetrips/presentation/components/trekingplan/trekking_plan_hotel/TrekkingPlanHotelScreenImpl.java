package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.jfoenix.controls.JFXButton;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.Rating;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanHotelScreenImpl extends FXMLScreen
implements TrekkingPlanHotelScreen, Initializable {

    private TrekkingPlanHotelPresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private Rating _ratingBar;

    @Inject
    public TrekkingPlanHotelScreenImpl(TrekkingPlanHotelPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {
        _btnNext.setDisable(!value);
    }

    @Override
    public void setLoading(boolean value) {

    }

    @Override
    public void navigateToPreview() {
        navigate(TrekkingPlanPreviewScreenImpl.class);
    }

    @FXML
    public void onNavigateToPreview()
    {
        _presenter.requestToNavigateToPreview();
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step4.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _ratingBar.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _presenter.onStarsUpdated((int)_ratingBar.getRating());
            }
        });
    }
}

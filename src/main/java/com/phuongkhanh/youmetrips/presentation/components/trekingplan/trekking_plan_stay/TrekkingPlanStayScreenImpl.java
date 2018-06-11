package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel.TrekkingPlanHotelScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanStayScreenImpl extends FXMLScreen
implements TrekkingPlanStayScreen, Initializable {

    private TrekkingPlanStayPresenter _presenter;

    @Inject
    public TrekkingPlanStayScreenImpl(TrekkingPlanStayPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {

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

    }
}

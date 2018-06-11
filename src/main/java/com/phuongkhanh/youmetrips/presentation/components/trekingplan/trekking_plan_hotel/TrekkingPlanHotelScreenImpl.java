package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanHotelScreenImpl extends FXMLScreen
implements TrekkingPlanHotelScreen, Initializable {

    @Inject
    public TrekkingPlanHotelScreenImpl(TrekkingPlanHotelPresenter presenter)
    {

    }

    @Override
    public void setCanNext(boolean value) {

    }

    @Override
    public void setLoading(boolean value) {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step4.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

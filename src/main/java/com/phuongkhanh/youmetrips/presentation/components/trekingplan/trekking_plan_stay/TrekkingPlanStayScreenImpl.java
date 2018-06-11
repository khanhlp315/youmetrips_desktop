package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanStayScreenImpl extends FXMLScreen
implements TrekkingPlanStayScreen, Initializable {

    @Inject
    public TrekkingPlanStayScreenImpl(TrekkingPlanStayPresenter presenter)
    {

    }

    @Override
    public void setCanNext(boolean value) {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step3.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

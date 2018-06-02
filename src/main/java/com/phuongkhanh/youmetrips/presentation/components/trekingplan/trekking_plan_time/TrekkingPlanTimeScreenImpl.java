package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanTimeScreenImpl extends FXMLScreen
implements TrekkingPlanTimeScreen, Initializable {

    @Inject
    public TrekkingPlanTimeScreenImpl(TrekkingPlanTimePresenter presenter)
    {

    }

    @Override
    public void setCanNext(boolean value) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

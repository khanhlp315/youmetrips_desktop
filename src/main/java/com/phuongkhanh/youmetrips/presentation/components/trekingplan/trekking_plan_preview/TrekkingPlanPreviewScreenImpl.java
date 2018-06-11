package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanPreviewScreenImpl extends FXMLScreen
implements TrekkingPlanPreviewScreen, Initializable {

    @Inject
    public TrekkingPlanPreviewScreenImpl(TrekkingPlanPreviewPresenter presenter)
    {

    }

    @Override
    public void setLoading(boolean value) {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step5.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

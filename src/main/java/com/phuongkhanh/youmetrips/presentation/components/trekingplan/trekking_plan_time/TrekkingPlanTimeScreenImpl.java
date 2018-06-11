package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_time;

import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanTimeScreenImpl extends FXMLScreen
implements TrekkingPlanTimeScreen, Initializable {
    private TrekkingPlanTimePresenter _presenter;

    @Inject
    public TrekkingPlanTimeScreenImpl(TrekkingPlanTimePresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {

    }

    @Override
    public void navigateToStay() {
        navigate(TrekkingPlanStayScreenImpl.class);
    }

    @FXML
    public void onNavigateToStay()
    {
        _presenter.requestToNavigateToStay();
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_plan/step2.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

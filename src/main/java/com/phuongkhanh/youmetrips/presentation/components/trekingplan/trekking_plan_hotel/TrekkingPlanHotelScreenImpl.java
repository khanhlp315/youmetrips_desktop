package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_hotel;

import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_preview.TrekkingPlanPreviewScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class TrekkingPlanHotelScreenImpl extends FXMLScreen
implements TrekkingPlanHotelScreen, Initializable {

    private TrekkingPlanHotelPresenter _presenter;

    @Inject
    public TrekkingPlanHotelScreenImpl(TrekkingPlanHotelPresenter presenter)
    {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void setCanNext(boolean value) {

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

    }
}

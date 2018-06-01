package com.phuongkhanh.youmetrips.presentation.components.planlist;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.UserTrekkingPlan;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlanListScreenImpl extends FXMLScreen
implements PlanListScreen, Initializable {
    @Override
    public void updatePlans(List<UserTrekkingPlan> plans) {

    }

    @Override
    public void navigateToPlanDetails(int planId) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

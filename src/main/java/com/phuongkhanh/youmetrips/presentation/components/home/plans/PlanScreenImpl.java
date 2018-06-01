package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlanScreenImpl extends FXMLScreen
implements PlanScreen, Initializable {
    @Override
    public void updatePlans(List<RelevantPlan> invidualPlans, List<RelevantPlan> groupPlans) {

    }

    @Override
    public void navigateToCreateTrekkingPlan() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void showAddFriendPopup(int userId) {

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

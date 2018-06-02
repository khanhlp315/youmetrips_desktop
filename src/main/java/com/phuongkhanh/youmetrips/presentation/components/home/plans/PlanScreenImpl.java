package com.phuongkhanh.youmetrips.presentation.components.home.plans;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.RelevantPlan;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlanScreenImpl extends FXMLScreen
implements PlanScreen, Initializable {

    @Inject
    public PlanScreenImpl(PlanPresenter presenter)
    {

    }

    @Override
    public void updatePlans(List<RelevantPlan> invidualPlans) {

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
        return "/view/home/profile/plan.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void onPlaceClicked()
    {

    }

    @FXML
    public void onFriendRequestClicked()
    {

    }

    @FXML
    public void onCreateTrekkingPlanClicked()
    {

    }

    @FXML
    public void onCreateTrekkingPlaceClicked()
    {

    }

    @FXML
    public void onProfileClicked()
    {

    }

    @FXML
    public void onEditProfileClicked()
    {

    }
}

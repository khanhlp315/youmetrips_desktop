package com.phuongkhanh.youmetrips.presentation.components.plandetails;

import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import com.phuongkhanh.youmetrips.services.api.models.Comment;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetails;
import javafx.fxml.Initializable;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlanDetailsScreenImpl extends FXMLScreen
implements PlanDetailsScreen, Initializable {

    @Inject
    public PlanDetailsScreenImpl(PlanDetailsPresenter presenter)
    {

    }

    @Override
    public void updateMapUrl(String mapUrl) {

    }

    @Override
    public void updateComments(List<Comment> comments) {

    }

    @Override
    public void updateUserAvatar(String avatar) {

    }

    @Override
    public void resetCommentTextField() {

    }

    @Override
    public void updatePlanDetails(PlanDetails planDetails) {

    }

    @Override
    protected String fxmlPath() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

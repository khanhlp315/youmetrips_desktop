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

    PlanDetailsPresenter _presenter;

    @Inject
    public PlanDetailsScreenImpl(PlanDetailsPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
        _presenter.fetchAvatar();
        _presenter.fetchPlanDetails(_presenter.getPlanId());
        _presenter.fetchComments(_presenter.getPlanId());
        _presenter.fetchAvatar();
    }

    @Override
    public void updateMapUrl(String mapUrl) {
        System.out.println(mapUrl);
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
        return "/view/home/plan_details_view.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

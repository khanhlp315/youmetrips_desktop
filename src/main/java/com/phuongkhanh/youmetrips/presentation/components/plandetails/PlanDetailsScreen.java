package com.phuongkhanh.youmetrips.presentation.components.plandetails;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Comment;
import com.phuongkhanh.youmetrips.services.api.models.PlanDetails;

import java.util.List;

public interface PlanDetailsScreen extends JFXScreen {
    void navigateBack();

    void updateMapUrl(String mapUrl);

    void updateComments(List<Comment> comments);

    void updateUserAvatar(String avatar);

    void resetCommentTextField();

    void updatePlanDetails(PlanDetails planDetails);

}

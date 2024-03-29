package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.home.friend_requests.FriendRequestsScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.places.PlaceScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.plans.PlanScreen;
import com.phuongkhanh.youmetrips.presentation.components.home.profile.ProfileScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place.TrekkingPlanPlaceScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_stay.TrekkingPlanStayScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class HomeWindow extends JFXWindowBase {
    @Inject
    public HomeWindow(
            final ProfileScreen profileScreen,
            final PlanScreen planScreen,
            final PlaceScreen placeScreen,
            final FriendRequestsScreen friendRequestsScreen) {
        super(profileScreen, planScreen, placeScreen, friendRequestsScreen);
    }
}
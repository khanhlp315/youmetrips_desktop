package com.phuongkhanh.youmetrips.presentation.components.trekingplan.trekking_plan_place;

import com.phuongkhanh.youmetrips.presentation.framework.JFXScreen;
import com.phuongkhanh.youmetrips.services.api.models.Place;

import java.util.List;

public interface TrekkingPlanPlaceScreen extends JFXScreen {
    void showContinue();

    void hideContinue();

    void updatePlaces(List<Place> places);

    void navigateToCreateTrekkingPlace();

    void updateMapUrl(String mapUrl);
}

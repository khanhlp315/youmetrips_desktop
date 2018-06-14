package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.place_details.PlaceDetailsScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class PlaceDetailsWindow extends JFXWindowBase {

    @Inject
    public PlaceDetailsWindow(final PlaceDetailsScreenImpl placeDetailsScreen)
    {
        super(placeDetailsScreen);
    }
}

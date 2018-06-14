package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_hashtags.TrekkingPlaceHashtagsScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location.TrekkingPlaceLocationScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_name.TrekkingPlaceNameScreen;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class CreatePlaceWindow extends JFXWindowBase {

    @Inject
    public CreatePlaceWindow(
            final TrekkingPlaceNameScreen nameScreen,
            final TrekkingPlaceLocationScreen locationScreen,
            final TrekkingPlacePhotosScreen photosScreen,
            final TrekkingPlaceHashtagsScreen hashtagsScreen
            )
    {
        super(nameScreen, locationScreen, photosScreen, hashtagsScreen);
    }
}

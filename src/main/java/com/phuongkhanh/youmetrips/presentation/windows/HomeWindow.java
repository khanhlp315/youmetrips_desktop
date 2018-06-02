package com.phuongkhanh.youmetrips.presentation.windows;

import com.phuongkhanh.youmetrips.presentation.components.home.HomeScreen;
import com.phuongkhanh.youmetrips.presentation.framework.JFXWindowBase;

import javax.inject.Inject;

public class HomeWindow extends JFXWindowBase {
    @Inject
    public HomeWindow(final HomeScreen homeScreen)
    {
        super(homeScreen);
    }
}
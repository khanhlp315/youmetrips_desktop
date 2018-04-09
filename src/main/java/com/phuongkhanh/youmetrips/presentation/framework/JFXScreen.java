package com.phuongkhanh.youmetrips.presentation.framework;

import javafx.scene.Scene;

public interface JFXScreen {
    Scene render();

    JFXWindow getWindow();

    <T> void navigate( final Class<T> clazz );

    void navigateBack();

    void onNavigation( final NavigationEvent event );
}

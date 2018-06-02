package com.phuongkhanh.youmetrips.presentation.framework;

import javafx.stage.Stage;

public interface JFXWindow {
    void attach(final Stage stage);

    Stage getStage();

    <T> void navigate(final Class<T> clazz);

    void navigateBack();

    JFXScreen[] getScreens();

    JFXScreen getCurrentScreen();

    void show();

    void close();

    void hide();
}

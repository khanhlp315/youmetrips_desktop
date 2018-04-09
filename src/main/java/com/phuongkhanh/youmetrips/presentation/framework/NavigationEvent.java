package com.phuongkhanh.youmetrips.presentation.framework;

public class NavigationEvent {
    private final JFXScreen _screenEnter;
    private final JFXScreen _screenExit;

    public NavigationEvent( final JFXScreen screenEnter, final JFXScreen screenExit ) {
        _screenEnter = screenEnter;
        _screenExit = screenExit;
    }

    public JFXScreen getScreenEnter() {
        return _screenEnter;
    }

    public JFXScreen getScreenExit() {
        return _screenExit;
    }
}

package com.phuongkhanh.youmetrips.presentation.framework;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Stack;

public class JFXWindowBase implements JFXWindow {
    private Stage _stage;
    private JFXScreen[] _screens;
    private JFXScreen _currentScreen;
    private Stack<JFXScreen> _navigatedScreenStack;


    protected JFXWindowBase(final JFXScreen... screens) {
        _screens = screens;
        _currentScreen = null;
        _navigatedScreenStack = new Stack<>();

        // validate screen list
        if (_screens.length == 0) {
            throw new RuntimeException("Empty screen list, windows must have at least 1 screen to renderer!");
        }

        // notify screens attached window
        for (JFXScreen screen : _screens) {
            if (screen instanceof FXMLScreen) {
                ((FXMLScreen) screen).setWindow(this);
            }
        }
    }


    @Override
    public void attach(final Stage stage) {
        // validate parameter
        if (stage == null) {
            throw new NullPointerException("Can not attach null stage!");
        }

        _stage = stage;
        _stage.initStyle(StageStyle.UTILITY);

        // reset navigation and show first screen
        _currentScreen = null;
        _navigatedScreenStack.clear();
        navigate(_screens[0]);
    }

    @Override
    public final Stage getStage() {
        return _stage;
    }


    @Override
    public <T> void navigate(final Class<T> clazz) {
        for (JFXScreen screen : _screens) {
            if (clazz.isInstance(screen)) {
                navigate(screen);
                return;
            }
        }

        // Invalid case: no screen matched
        throw new RuntimeException(String.format("Can not navigate, screen of type %s has not been defined!", clazz.getSimpleName()));
    }

    @Override
    public void navigateBack() {
        // validate navigation stack
        if (_navigatedScreenStack.empty()) {
            throw new RuntimeException("Can not navigate back, there is no screen stack before current screen!");
        }

        navigate(_navigatedScreenStack.peek());
    }

    private void navigate(final JFXScreen screen) {
        // validate stage and screen
        if (_stage == null) {
            throw new NullPointerException("Can not navigate, no stage attached. Stage must be attached before doing navigation!");
        }
        if (screen == null) {
            throw new NullPointerException("Can not navigate to null screen!");
        }
        if (_currentScreen == screen) {
            throw new RuntimeException("Invalid navigation, can not navigate to a screen from itself!");
        }

        // check for back navigation
        boolean isBack = _navigatedScreenStack.contains(screen);

        if (isBack) {
            // play transition exit stack
            while (screen != _navigatedScreenStack.peek()) {
                _navigatedScreenStack.pop();
            }
            setCurrentScreen(_navigatedScreenStack.pop());
        } else {
            // play transition enter stack
            _navigatedScreenStack.push(_currentScreen);
            setCurrentScreen(screen);
        }
    }


    @Override
    public final JFXScreen[] getScreens() {
        return _screens;
    }

    @Override
    public final JFXScreen getCurrentScreen() {
        return _currentScreen;
    }

    @Override
    public void show() {
        if (_stage == null) {
            throw new RuntimeException("Can not show, no stage attached. Stage must be attached before being able to show!");
        }
        if (_stage.isShowing()) {
            throw new RuntimeException("Window is currently showing!");
        }

        _stage.show();

    }

    @Override
    public void showAndWait() {
        if ( _stage == null ) {
            throw new RuntimeException( "Can not show, no stage attached. Stage must be attached before being able to show!" );
        }
        if ( _stage.isShowing() ) {
            throw new RuntimeException( "Window is currently showing!" );
        }

        _stage.showAndWait();
    }

    @Override
    public void close() {
        if (_stage == null) {
            throw new RuntimeException("Can not close, no stage attached. Stage must be attached before being able to close!");
        }

        _stage.close();
    }

    @Override
    public void hide() {
        if (_stage == null) {
            throw new RuntimeException("Can not hide, no stage attached. Stage must be attached before being able to show!");
        }
        if (!_stage.isShowing()) {
            throw new RuntimeException("Can not hide, window is not showing. Only can hide when window is showing!");
        }

        _stage.hide();

    }

    private void setCurrentScreen(final JFXScreen screen) {
        JFXScreen oldScreen = _currentScreen;
        _currentScreen = screen;
        _stage.setScene(_currentScreen.render());

        // notify navigation event
        NavigationEvent navigationEvent = new NavigationEvent(_currentScreen, oldScreen);
        if (oldScreen != null) oldScreen.onNavigation(navigationEvent);
        if (_currentScreen != null) _currentScreen.onNavigation(navigationEvent);
    }
}

package com.phuongkhanh.youmetrips.presentation.framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public abstract class FXMLScreen implements JFXScreen {
    private JFXWindow _window;
    private Scene     _renderer;


    protected FXMLScreen() {
        if ( !lazy() ) {
            // eager load
            loadRenderer( fxmlPath() );
        }
    }

    protected abstract String fxmlPath();

    protected boolean lazy() {
        return false;
    }


    @Override
    public Scene render() {
        if ( _renderer == null ) {
            loadRenderer( fxmlPath() );
        }
        return _renderer;
    }

    protected final Scene getRenderer() {
        return _renderer;
    }

    private void loadRenderer( final String fxmlPath ) {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( fxmlPath ) );
        fxmlLoader.setController( this );

        try {
            _renderer = new Scene( fxmlLoader.load() );
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }


    protected void setWindow( final JFXWindow window ) {
        _window = window;
    }

    @Override
    public final JFXWindow getWindow() {
        return _window;
    }


    @Override
    public <T> void navigate( final Class<T> clazz ) {
        if ( _window == null ) {
            throw new RuntimeException( "Can not navigate, screen has not been rendered. Screen must be rendered before doing navigation" );
        }
        _window.navigate( clazz );
    }

    @Override
    public void navigateBack() {
        if ( _window == null ) {
            throw new RuntimeException( "Can not navigate, screen has not been rendered. Screen must be rendered before doing navigation" );
        }
        _window.navigateBack();
    }

    @Override
    public void onNavigation( final NavigationEvent event ) {
    }
}


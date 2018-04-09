package com.phuongkhanh.youmetrips.presentation.framework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class FXMLView implements JFXView {
    private Parent _renderer;


    protected FXMLView() {
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
    public Parent render() {
        if ( _renderer == null ) {
            loadRenderer( fxmlPath() );
        }
        return _renderer;
    }

    protected final Parent getRenderer() {
        return _renderer;
    }

    private void loadRenderer( final String fxmlPath ) {
        FXMLLoader fxmlLoader = new FXMLLoader( getClass().getResource( fxmlPath ) );
        fxmlLoader.setController( this );

        try {
            _renderer = fxmlLoader.load();
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }
}


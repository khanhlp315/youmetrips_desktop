package com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_location;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.MapStateEventType;
import com.lynden.gmapsfx.javascript.event.StateEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.phuongkhanh.youmetrips.presentation.components.trekkingplace.trekking_place_photos.TrekkingPlacePhotosScreenImpl;
import com.phuongkhanh.youmetrips.presentation.framework.FXMLScreen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.inject.Inject;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TrekkingPlaceLocationScreenImpl extends FXMLScreen
        implements TrekkingPlaceLocationScreen, Initializable, MapComponentInitializedListener {

    private TrekkingPlaceLocationPresenter _presenter;

    @FXML
    private JFXButton _btnNext;

    @FXML
    private Label _tfPlace;

    @FXML
    private GoogleMapView _ggMaps;

    private GoogleMap map;

    private DecimalFormat formatter = new DecimalFormat("###.00000");
    private Marker _marker;

    @Inject
    public TrekkingPlaceLocationScreenImpl(TrekkingPlaceLocationPresenter presenter) {
        _presenter = presenter;
        _presenter.setView(this);
    }

    @Override
    public void showContinue() {
        _btnNext.setDisable(false);
    }

    @Override
    public void hideContinue() {
        _btnNext.setDisable(true);
    }

    @Override
    public void showMap() {

    }

    @Override
    public void showError(String title, String message) {

    }

    @Override
    public void navigateToPhotos() {
        navigate(TrekkingPlacePhotosScreenImpl.class);
    }

    @FXML
    public void onNavigateToPhotos() {
        _presenter.requestToNavigateToPhotos(_tfPlace.getText());
    }

    @Override
    protected String fxmlPath() {
        return "/view/create_place/step2.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        _tfPlace.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                _presenter.onLocationUpdated(_tfPlace.getText());
            }
        });
        _ggMaps.addMapInializedListener(this);
    }

    private void configureMap() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(27.974538, 86.928067))
                .mapType(MapTypeIdEnum.TERRAIN)
                .zoom(10);
        map = _ggMaps.createMap(mapOptions, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLong(27.974538, 86.928067))
                .visible(true)
                .title("marker");

        _marker = new Marker(markerOptions);
        map.addMarker(_marker);



        map.addStateEventHandler(MapStateEventType.drag, new StateEventHandler() {
            @Override
            public void handle() {
                LatLong latLong = _ggMaps.getMap().getCenter();
                System.out.println(latLong);
                _tfPlace.setText(String.format("%d, %d", formatter.format(latLong.getLatitude()), formatter.format(latLong.getLongitude())));
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLong)
                        .visible(true)
                        .title("marker");

                _marker = new Marker(markerOptions);
                //_ggMaps.getMap().addMarker();
                //map.removeMarker(_marker);
                //map.addMarker(_marker);
            }});



        map.addMouseEventHandler(UIEventType.click,(GMapMouseEvent event) -> {

        } );

    }

    @Override
    public void mapInitialized() {
        LatLong center = new LatLong(27.974538, 86.928067);
        MapOptions options = new MapOptions();
        options.center(center)
                .zoom(10)
                .mapType(MapTypeIdEnum.TERRAIN)
                .streetViewControl(false);
        map = _ggMaps.createMap(options, false);
        map.addStateEventHandler(MapStateEventType.dragend, () -> {
            _tfPlace.setText(String.format("%f, %f", _ggMaps.getMap().getCenter().getLatitude(), _ggMaps.getMap().getCenter().getLongitude()));
            map.clearMarkers();
            createMarker(_ggMaps.getMap().getCenter());
            map.addMarker(_marker);
        });
        createMarker(center);
        _tfPlace.setText(String.format("%f, %f", center.getLatitude(), center.getLongitude()));
        map.addMarker(_marker);
    }

    public void createMarker(LatLong latLong){
        MarkerOptions options = new MarkerOptions();
        options.position(latLong)
                .visible(true);
        _marker = new Marker(options);
    }
}

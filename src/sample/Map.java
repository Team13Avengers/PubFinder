package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * Created by Marco on 2015-11-12.
 */

public class Map implements Initializable, MapComponentInitializedListener {

    public static GoogleMapView mapView;
    public static GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }
    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add a marker to the map
        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(47.6, -122.3) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);

    }
}

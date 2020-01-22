package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;
import cs2340.gatech.edu.lab4.model.Shelter;

public class MapsActivity123 extends AppCompatActivity implements OnMapReadyCallback {

    private static GoogleMap mMap;
    private boolean mTwoPane;
    public static final String ARG_GENDER = "gender";
    public static Gender currentGenderSearchOption = Gender.ALL;
    public static final String ARG_AGE = "age";
    public static Age currentAgeSearchOption = Age.ALL;

    /**
     * Sets up maps activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps123);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //save the map instance returned from Google
        mMap = googleMap;



        //reference to our GRASP Controller interface to the model
        final SearchController dataService = SearchController.getInstance();

        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                //add a new item where the touch happened, for non-hardcoded data, we would need
                //to launch an activity with a form to enter the data.
                //dataService.addDataElement("newly added", "Bobs Place", new Location(latLng.latitude, latLng.longitude));

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                //markerOptions.title(dataService.getLastElementAdded().getName());
                //markerOptions.snippet(dataService.getLastElementAdded().getDescription());

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                //mMap.addMarker(markerOptions);
            }
        });

        //get the data to display
        List<Shelter> dataList = dataService.getSearchResult();

        //iterate through the list and add a pin for each element in the model
        for (Shelter de : dataList) {
            LatLng loc = new LatLng(de.getLatitude(), de.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(de.getName()).snippet(de.getPhoneNumber()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }

        //Use a custom layout for the pin data
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }
    public static GoogleMap getMap() {
        return mMap;
    }

    /**
     * This class implements a custom layout for the pin
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        /**
         * Make the adapter
         */
        CustomInfoWindowAdapter() {
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView) myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView) myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
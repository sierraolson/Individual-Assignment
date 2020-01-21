package cs2340.gatech.edu.lab4.controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.Arrays;
import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;

import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_AGE;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.ARG_GENDER;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentAgeSearchOption;
import static cs2340.gatech.edu.lab4.controller.ShelterListActivity.currentGenderSearchOption;

/**
 * Created by Zumong on 3/4/18.
 */

public class ShelterSearchPopup extends Activity {

    private static List<Gender> genderCategory = Arrays.asList(Gender.ALL, Gender.MALE,Gender.FEMALE);
    private static List<Age> ageCategory = Arrays.asList(Age.ALL, Age.CHILDREN, Age.NEWBORN, Age.YOUNG_ADULTS);
    static SearchController sc = SearchController.getInstance();
//    private static GoogleMap mMap;


    private Spinner genderSpinner;
    private Spinner ageSpinner;
    private EditText searchBar;

    /**
     * Sets up shelter search popup with options, drop downs, etc.
     * @param savedInstanceState prior state (possibly null)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_search_option_popup);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        genderSpinner = findViewById(R.id.gender_spinner);
        ageSpinner = findViewById(R.id.age_spinner);
        searchBar = findViewById(R.id.search_bar);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, genderCategory);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        ArrayAdapter<String> ageAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, ageCategory);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        if (getIntent().hasExtra(ARG_GENDER)) {
            genderSpinner.setSelection(findPosition(currentGenderSearchOption));
        }
        if (getIntent().hasExtra(ARG_AGE)) {
            ageSpinner.setSelection(findPosition(currentAgeSearchOption));
        }
    }

    /**
     * Event listener that engages search functionality
     * @param view
     */
    public void onSearchPressed(View view) {
        currentGenderSearchOption = (Gender) genderSpinner.getSelectedItem();
        currentAgeSearchOption = (Age) ageSpinner.getSelectedItem();
        sc.search(searchBar.getText().toString(), (Gender)genderSpinner.getSelectedItem(), (Age)ageSpinner.getSelectedItem());

//        //save the map instance returned from Google
//        mMap = MapsActivity123.getMap();
//
//
//
//        //reference to our GRASP Controller interface to the model
//        final DataServiceFacade dataService = DataServiceFacade.getInstance();
//
//        // Setting a click event handler for the map
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//            @Override
//            public void onMapClick(LatLng latLng) {
//
//                // Creating a marker
//                MarkerOptions markerOptions = new MarkerOptions();
//
//                // Setting the position for the marker
//                markerOptions.position(latLng);
//
//                //add a new item where the touch happened, for non-hardcoded data, we would need
//                //to launch an activity with a form to enter the data.
//                //dataService.addDataElement("newly added", "Bobs Place", new Location(latLng.latitude, latLng.longitude));
//
//                // Setting the title for the marker.
//                // This will be displayed on taping the marker
//                //markerOptions.title(dataService.getLastElementAdded().getName());
//                //markerOptions.snippet(dataService.getLastElementAdded().getDescription());
//
//                // Animating to the touched position
//                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//                // Placing a marker on the touched position
//                //mMap.addMarker(markerOptions);
//
//            }
//        });
//
//        //get the data to display
//        List<DataElement> dataList = dataService.getData();
//
//        //iterate through the list and add a pin for each element in the model
//        for (DataElement de : dataList) {
//            LatLng loc = new LatLng(de.getLatitude(), de.getLongitude());
//            mMap.addMarker(new MarkerOptions().position(loc).title(de.getName()).snippet(de.getDescription()));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
//        }
//
//        //Use a custom layout for the pin data
//        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        finish();
    }

    /**
     * aborts search functionality
     * @param view current view
     */
    public void onCancelPressed(View view) {
        finish();
    }

    /**
     * Finds location in gender enum
     * @param code
     * @return
     */
    public static int findPosition(Enum code) {
        if(code instanceof Gender) {
            int i = 0;
            while (i < genderCategory.size()) {
                if (code.equals(genderCategory.get(i))) return i;
                ++i;
            }
        }
        return 0;
    }

    /**
     * Getter for search controller
     * @return SearchController sc
     */
    public static SearchController getSearchController(){
        return sc;
    }
    /**
     * This class implements a custom layout for the pin
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        /**
         * Make the adapter
         */
        CustomInfoWindowAdapter(){
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = ((TextView)myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
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

package cs2340.gatech.edu.lab4.controller;



import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import cs2340.gatech.edu.lab4.model.Shelter;

/**
 * Created by robertwaters on 3/13/18.
 *
 * Structurer
 *
 * primary responsibility is to manage a group of DataElements
 */

class DataManager {
    public static List<DataElement> theData;
    ArrayList<Shelter> searchResults;
    SearchController sc;

    DataManager() {
        theData = new ArrayList<>();
        makeSomeData();
    }

    /**
     * Populates the shelter ArrayList with data elements
     */
    private void makeSomeData() {
        sc = ShelterSearchPopup.getSearchController();
        searchResults = sc.getSearchResult();
        for (Shelter x : searchResults) {
            addReport(new DataElement(x.getName(), x.getPhoneNumber(), new Location(x.getLatitude(), x.getLongitude())));
        }


        //addReport(new DataElement("Coke Zero", "Sam's Deli", new Location(33.749, -84.388)));
       // addReport(new DataElement("Pepsi", "Grandma Garage", new Location(33.8, -84.5)));

    }

    /**
     *  Puts DataElement structure in theData List
     * @param de DataElement to be added
     */
    void addReport(DataElement de) {
        theData.add(de);
    }

    /**
     * getter for element's data
     * @return the data held by the DataElement
     */
    List<DataElement> getData() { return theData; }



}

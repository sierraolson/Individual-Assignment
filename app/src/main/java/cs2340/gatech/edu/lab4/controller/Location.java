package cs2340.gatech.edu.lab4.controller;

/**
 * Created by robertwaters on 3/13/18.
 *
 * Information Holder
 *
 * Maintains information about Latitude and Longitude
 */

public class Location {
    private double _latitude;
    private double _longitude;

    public static Location curLocation;

//    public void init(double lat, double lon) {
//        curLocation = new Location((lat, lon)
//    }

    /**
     * Creates a new Location
     * @param lat  the latitude
     * @param longit  the longitude
     */
    public Location(double lat, double longit) {
      _latitude = lat;
        _longitude = longit;
    }

    /**
     *
     * @return  the latitude value
     */
     public double getLatitude() { return _latitude; }

    /**
     *
     * @return the longitude value
     */
    public double getLongitude() { return _longitude; }
}
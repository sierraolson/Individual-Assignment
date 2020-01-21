package cs2340.gatech.edu.lab4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 2/21/18.
 */

public class Shelter {

    /**the shelter's id number*/
    private int uniqueKey;

    /**the shelter's name*/
    private String shelterName;

    /**the shelter's capacity*/
    private String capacity;

    /**the shelter's restrictions*/
    private String restrictions;

    /**the shelter's longitude*/
    private float longitude;

    /**the shelter's latitude*/
    private float latitude;

    /**the shelter's address*/
    private String address;

    /**the shelter's special notes*/
    private String specialNotes;

    /**the shelter's phone number*/
    private String phoneNumber;

    private List<String> _details;

    private List<String> _headers;

    /**the number of beds available to claim*/
    private int availableBeds;
    /******************************
     * Getters
     */
    public int getKey() {
        return uniqueKey;
    }
    public String getName() {
        return shelterName;
    }
    public String getCapacity() {
        return capacity;
    }
    public String getRestrictions() {
        return restrictions;
    }
    public float getLongitude() {
        return longitude;
    }
    public float getLatitude() {
        return latitude;
    }
    public String getAddress() {
        return address;
    }
    public String getSpecialNotes () {
        return specialNotes;
    }
    public List<String> getDetails() {
        return _details;
    }
    public List<String> getHeaders() {return _headers;}
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public int getAvailableBeds() {return availableBeds;}
    public void setDetails() {
        _details = new ArrayList<>();
        _details.add(shelterName);
        _details.add(capacity);
        _details.add(address);
        _details.add(specialNotes);
        _details.add(phoneNumber);
        _details.add(Integer.toString(availableBeds));
    }
    public void setHeaders() {
        _headers = new ArrayList<>();
        _headers.add("Name:");
        _headers.add("Capacity:");
        _headers.add("Address:");
        _headers.add("Notes:");
        _headers.add("Phone Number:");
        _headers.add("Beds Available: ");

    }

    /**
     * Constructor for Shelter object
     * @param key unique shelter id
     * @param name name of shelter
     * @param cap capacity (e.g. total available beds)
     * @param aBed number of beds currently available
     * @param restr restrictions (gender, age)
     * @param longi longitudinal information
     * @param lati latitudinal information
     * @param addr shelter address
     * @param note note on the shelter
     * @param phoneNum shelter contact phone
     */
    public Shelter(int key, String name, String cap, int  aBed, String restr, float longi, float lati, String addr, String note, String phoneNum) {
        uniqueKey = key;
        shelterName = name;
        capacity = cap;
        restrictions = restr;
        longitude = longi;
        latitude = lati;
        address = addr;
        specialNotes = note;
        phoneNumber = phoneNum;
        availableBeds = aBed;
    }
    public Shelter() {

    }

    /**
     * Setter for amount of beds available
     * @param beds number of beds to update to
     */
    public void setAvailableBeds(int beds) {
        availableBeds = beds;
    }

    /**
     * To string for shelter name
     * @return name of shelter
     */
    public String toString() {
        return shelterName;
    }

    /**
     * Checks if two shelters are the same one
     * @param s shelter being compared to
     * @return true if they're the same, false otherwise
     */
    public boolean equals(Shelter s) {
        if(!(s instanceof Shelter)) {
            return false;
        }

        return this.toString().equals(s.toString());
    }

}

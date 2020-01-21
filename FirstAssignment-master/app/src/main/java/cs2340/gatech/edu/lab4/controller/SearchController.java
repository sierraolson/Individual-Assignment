package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;
import cs2340.gatech.edu.lab4.model.Shelter;
/*
 * Created by Zumong on 3/3/18.
 */

public class SearchController {

    private static ArrayList<Shelter> _searchResult = new ArrayList<>(Model.getShelters());;

    public static SearchController getInstance() { return _instance; }
    private static SearchController _instance = new SearchController();



    private SearchController() {
    }

    /**
     * Getter for results of search
     * @return the result of a search
     */
    public ArrayList<Shelter> getSearchResult() { return _searchResult; }
    /**
     * search that filters by shelter name, age required, and gender restrictions
     * @param g gender restriction user needs
     * @param a age restriction user needs
     */
    public void search(String searchStr, Gender g, Age a) {
        _searchResult.clear();
        if(!searchStr.trim().isEmpty()){
            searchName(searchStr.trim());
        } else {
            filterByRestriction(g,a);
        }

    }

    /**
     * Search without filtration, only using name
     * @param searchStr name being searched
     */
    private void searchName(String searchStr) {
        for (Shelter s: Model.getShelters()){
            if (s.getName().contains(searchStr)) {
                _searchResult.add(s);
            }
        }
    }

    /**
     * Filters shelters based on age and gender restrictions
     * @param pg gender being checked
     * @param pa age being checked
     */
    private void filterByRestriction(Gender pg, Age pa) {
        List<Shelter> temp = new ArrayList<>();
        //filter Model.getShelter by pg
        if(!pg.equals(Gender.ALL)){
            for (Shelter s : Model.getShelters()) { //Here Model
                Gender g = findGenderOfThis(s);
                if(pg.equals(g) || g.equals(Gender.ALL)) {
                    Log.d("Edit", "pg : " + pg.toString() + ",,,g: " + g.toString());
                    temp.add(s);
                }
            }
        } else {
            for (Shelter s : Model.getShelters()) {
                temp.add(s);
            }
        }
        //filter "temp" by pa, and store to _searchResult
        if(!pa.equals(Age.ALL)) {
            for (Shelter s : temp) {                //Here temp!!!
                Iterable<Age> arr = findAgeOfThis(s);
                for(Age a : arr) {
                    if(pa.equals(a) || a.equals(Age.ALL)) {
                        Log.d("Edit", "pa : " + pg.toString() + ",,,a: " + a.toString());
                        _searchResult.add(s);
                    }
                }
            }
        } else {
            for (Shelter s: temp) {
                _searchResult.add(s);
            }
        }
        System.out.println(_searchResult);
    }

    /**
     * gender restriction must be only one of ALL, MALE, FEMALE
     * str.equals method used.
     * return the Enum Gender of given shelter.
     * @param s Shelter being checked
     * @return the genders allowed at Shelter s s
     */
    private Gender findGenderOfThis(Shelter s) {
        Gender ret = Gender.ALL;
        String r_shelter = s.getRestrictions(); // Women/Children
        String[] r_factored = r_shelter.split("/"); // -> ["Women", "Children"]
        for (String r: r_factored) { //"Women"
            r = r.trim().toLowerCase(); // "women"
            for(Gender g: Gender.values()) { // g will be an element of [ALL("Anyone"), MALE("Men"), FEMALE("Women")]
                if (r.equals(g.toString().toLowerCase())) { // does "women" equals "anyone" ?
                    Log.d("Edit","r : " + r + "contains :" + g.toString().toLowerCase());
                    ret = Gender.getEnum(g.toString());
                }
            }
        }
        return ret;
    }

    /**
     * Shelter can be combination of Newborn, Children, Young Adults so this method returns array
     * str.contains method used.
     * @param s the shelter being checked
     * @return  ArrayList of acceptable ages for the shelter
     */
    private ArrayList<Age> findAgeOfThis(Shelter s) {
        ArrayList<Age> retArr = new ArrayList<>();
        Age ret = Age.ALL;
        String r_shelter = s.getRestrictions(); //
        r_shelter = r_shelter.trim().toLowerCase(); //
        for(Age g: Age.values()) {
            Log.d("Edit","r : " + r_shelter + "contains :" + g.toString().toLowerCase());
            if (r_shelter.contains(g.toString().toLowerCase())) { // ex)  "Families w/ children under 5" contains "children"?
                Log.d("Edit","r : " + r_shelter + "contains :" + g.toString().toLowerCase());
                ret = Age.getEnum(g.toString());
                retArr.add(ret);
            }
        }

        Log.d("Edit","Age ret:    " + ret.toString());
//        if (retArr.size() == 0) {
//            // if shelter has no restriction for age, then add Age.ALL("Anyone")
//            // but this code can cause the situation like this:
//            // A Shelter which has only gender restriction "Men" can be popped after filter by "Newborns"
//            retArr.add(Age.ALL);
//        }
        return retArr;
    }




}



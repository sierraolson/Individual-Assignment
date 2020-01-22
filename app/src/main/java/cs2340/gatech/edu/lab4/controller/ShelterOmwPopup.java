package cs2340.gatech.edu.lab4.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

import static cs2340.gatech.edu.lab4.controller.ShelterDetailFragment.ARG_COURSE_ID;

/**
 * Created by Owner on 3/23/2018.
 */

public class ShelterOmwPopup extends Activity {

    /**
     * The fragment arguments representing the  ID's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_COURSE_ID = "course_id";

    /**
     * The shelter that this detail view is for.
     */
    private Shelter mShelter;
    Model model = Model.getInstance();

    /**
     * Sets up popup
     * @param savedInstanceState prior state if available, possibly null
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelter_omw_option_popup);
        ////////////////////////////////////////////////////////////////////////////////////
        //Check if we got a valid course passed to us

            // Get the id from the intent arguments (bundle) and
            //ask the model to give us the course object
            final Model model = Model.getInstance();
            // mShelter = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
            mShelter = model.getCurrentShelter();
            if (mShelter.getDetails() == null) {
                mShelter.setDetails();
                mShelter.setHeaders();
            }
            Log.d("CourseDetailFragment", "Passing over shelter: " + mShelter);
            Log.d("CourseDetailFragment", "Address: " + mShelter.getAddress());

            final double capacity = Double.valueOf(mShelter.getCapacity());


        /////////////////////////////////////////////////////////////////////////////////////////

        Button reserve = (Button) findViewById(R.id.reserve);

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numOfGuests = (EditText) findViewById(R.id.numofGuests);
                double numOfGuest = Double.valueOf(numOfGuests.getText().toString());
                if (numOfGuest > capacity) {
                    Snackbar.make(view, "There are not that many spaces available", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } /*else {
                    model.child("capacity").setValue(Double.toString(capacity - 1));
                }*/

            }
        });
    }
}


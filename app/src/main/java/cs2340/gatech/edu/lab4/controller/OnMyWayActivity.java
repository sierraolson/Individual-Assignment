package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

public class OnMyWayActivity extends AppCompatActivity {
    private static Shelter shelter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_my_way);
    }

    /**
     * Event listener for cancel button, stops omw functionality if pressed
     * @param view current view
     */
    public void onCancelPressed(View view){
        shelter = Model.getInstance().getCurrentShelter();
        Log.d("##########", "current shelter vacancy: " + shelter.getAvailableBeds());
        int numBeds = Integer.parseInt(getIntent().getStringExtra("numReserved"));
        int newNumBeds = (shelter.getAvailableBeds() + numBeds);
        if (newNumBeds > Integer.parseInt(shelter.getCapacity()) ) {
            newNumBeds = Integer.parseInt(shelter.getCapacity());
        }
        FirebaseController.updateAvailableBeds(shelter, newNumBeds);
        shelter.setAvailableBeds(newNumBeds);

        Runnable startOnMyWayActivity = new Runnable() {
            public void run() {
                finish();               }
        };
        FirebaseController.getInstance().updateShelterListInModel();
        Handler handler = new Handler();
        Snackbar.make(view, "canceling reservation, wait for sec", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        handler.postDelayed(startOnMyWayActivity, 3000);

    }

    /**
     * Event listener for arrived button user presses upon arriving at shelter
     * @param view current view
     */
    public void onArrivedPressed(View view) {
        Intent intent = new Intent(this, ShelterDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}

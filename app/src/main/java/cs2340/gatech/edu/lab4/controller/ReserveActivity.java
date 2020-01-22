package cs2340.gatech.edu.lab4.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

/**
 * Created by Abigail Cliche on 3/26/2018.
 */

public class ReserveActivity extends AppCompatActivity {
    Model model = Model.getInstance();
    private TextView shelterName;
    private TextView availableBeds;
    private Spinner resNumberSpinner;
    private static Shelter currentShelter;
    private int numBeds;
    private int newNumBeds;

    /**
     * Sets up reserve activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        currentShelter= Model.getInstance().getCurrentShelter();
        shelterName = findViewById(R.id.shelterName);
        shelterName.setText(currentShelter.toString());
        availableBeds = findViewById(R.id.bedNumber);

        int numAvailableBeds = currentShelter.getAvailableBeds();
        Log.d("E", "----------------num available beds:" + numAvailableBeds);
        availableBeds.setText(""+numAvailableBeds);
        resNumberSpinner = findViewById(R.id.bedSpinner);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item);
        adapter.addAll(1,2,3,4,5);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (adapter != null) {
            resNumberSpinner.setAdapter(adapter);
        }

    }
    /**send to firebase and activity*/
    public void onOkayPressed(View view){
        numBeds = (int)resNumberSpinner.getSelectedItem();
        newNumBeds = (currentShelter.getAvailableBeds() - numBeds);
        if (newNumBeds < 0) {
            Snackbar.make(view, "There are not enough beds available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            FirebaseController.getInstance().updateAvailableBeds(currentShelter, newNumBeds);
            currentShelter.setAvailableBeds(newNumBeds);


            Runnable startOnMyWayActivity = new Runnable() {
                public void run() {
                    Intent intent = new Intent(getBaseContext(), OnMyWayActivity.class);
                    intent.putExtra("numReserved", numBeds+"");
                    startActivity(intent);                }
            };

            FirebaseController.getInstance().updateShelterListInModel();
            Handler handler = new Handler();
            Snackbar.make(view, "confirming reservation, wait for sec", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            handler.postDelayed(startOnMyWayActivity, 3000);
            model.updateBedsAvailable(currentShelter);


        }
    }

    /**
     * Aborts reserve action
     * @param view current view
     */
    public void onCancelPressed(View view) {
        finish();
    }

}


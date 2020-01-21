package cs2340.gatech.edu.lab4.controller;

/**
 * Created by mike on 2/22/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import cs2340.gatech.edu.lab4.R;

/**
 * An activity representing a single Shelter detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ShelterListActivity}.
 *
 * Here we need to display a list of shelter details.
 */
public class ShelterDetailActivity extends AppCompatActivity {
    /**
     * Sets up for detailed view of a shelter
     * @param savedInstanceState past instance if still available
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_detail);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        //if (savedInstanceState == null) {
        if (true) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.  Pass the course info to
            //the fragment
            Bundle arguments = new Bundle();
            arguments.putInt(ShelterDetailFragment.ARG_COURSE_ID,
                    getIntent().getIntExtra(ShelterDetailFragment.ARG_COURSE_ID, 0));

            ShelterDetailFragment fragment = new ShelterDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.course_detail_container, fragment)
                    .commit();
        }
    }

    /**
     * Triggers the reservation function
     * @param view current view
     */
    public void onReserveClick(View view) {
        Intent intent = new Intent(getBaseContext(), ReserveActivity.class);
        startActivity(intent);
    }

    /**
     * implements selection of possible options
     * @param item the option selected
     * @return boolean false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ShelterListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


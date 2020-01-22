package cs2340.gatech.edu.lab4.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.SearchCategory.Age;
import cs2340.gatech.edu.lab4.model.SearchCategory.Gender;
import cs2340.gatech.edu.lab4.model.Shelter;

/**
 * THIS IS OUR TOP_LEVEL WINDOW THAT THE USER FIRST SEES IN THE APPLICATION!
 *
 * An activity representing a list of Shelters. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ShelterDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 *
 * This is using a RecyclerView, which is the preferred standard for displaying
 * lists of things like our courses.
 */
public class ShelterListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.  For 2340, this is optional, since multi-display support is extra credit.
     */
    private boolean mTwoPane;
    public static final String ARG_GENDER = "gender";
    public static Gender currentGenderSearchOption = Gender.ALL;
    public static final String ARG_AGE = "age";
    public static Age currentAgeSearchOption = Age.ALL;

    private SimpleCourseRecyclerViewAdapter adapter;


    /**
     * Sets up list activity of shelters available
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = findViewById(R.id.course_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        //this is only needed if you are doing an optional support for multiple display sizes
        if (findViewById(R.id.course_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }


    }

    /**
     * continues activity after resume
     */
    @Override
    public void onResume() {
        super.onResume();
        setContentView(R.layout.activity_shelter_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View recyclerView = findViewById(R.id.course_list);

        setupRecyclerView((RecyclerView) recyclerView);

    }


    /**
     * Option Menu will be created on toolbar
     * @param menu the menu to be created
     * @return must return true for the menu to be displayed; if false it will not be shown.

     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shleter_list_options, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView =
//                (SearchView) searchItem.getActionView();
//
//        // Configure the search info and add any event listeners...

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This method handle when a item from menu is chosen.
     * When each menu is chosen, a window pop-up as a new Activity.
     * Pop-up window
     * @param item menu item being chosen
     * @return  Return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_option:
                Intent myIntent = new Intent(getApplicationContext(), ShelterSearchPopup.class);
                myIntent.putExtra(ARG_GENDER,currentGenderSearchOption);
                myIntent.putExtra(ARG_AGE, currentAgeSearchOption);
                startActivity(myIntent);
                return true;

            case R.id.logout:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            case R.id.map:
                Intent mapIntent = new Intent(getApplicationContext(), MapsActivity123.class);
                mapIntent.putExtra(ARG_GENDER,currentGenderSearchOption);
                mapIntent.putExtra(ARG_AGE, currentAgeSearchOption);
                startActivity(mapIntent);
                return true;

            case R.id.weather:
                Intent profileIntent = new Intent(getApplicationContext(), WeatherActivity .class);
                startActivity(profileIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * Set up an adapter and hook it to the provided view
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleCourseRecyclerViewAdapter(SearchController.getInstance().getSearchResult()));
        recyclerView.invalidate();
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public class SimpleCourseRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleCourseRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Shelter> mShelters;


        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleCourseRecyclerViewAdapter(List<Shelter> items) {
            mShelters = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*

              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shelter_list_content, parent, false);
            return new ViewHolder(view);
        }

        /**
         * Matches list elements with correct views
         * @param holder holds the view
         * @param position place within list
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            final Model model = Model.getInstance();
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mShelter = mShelters.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            holder.mIdView.setText("" + mShelters.get(position).getKey());
            holder.mContentView.setText(mShelters.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        //if a two pane window, we change the contents on the main screen
                        Bundle arguments = new Bundle();
                        arguments.putInt(ShelterDetailFragment.ARG_COURSE_ID, holder.mShelter.getKey());

                        ShelterDetailFragment fragment = new ShelterDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.course_detail_container, fragment)
                                .commit();
                    } else {
                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        Intent intent = new Intent(getBaseContext(), ShelterDetailActivity.class);
                        /*
                            pass along the id of the course so we can retrieve the correct data in
                            the next window
                         */
                        intent.putExtra(ShelterDetailFragment.ARG_COURSE_ID, holder.mShelter.getKey());

                        model.setCurrentShelter(holder.mShelter);

                        //now just display the new window
                        context.startActivity(intent);
                    }
                }
            });
        }

        /**
         * returns number of items in the list
         * @return list size
         */
        @Override
        public int getItemCount() {
            return mShelters.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Shelter mShelter;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

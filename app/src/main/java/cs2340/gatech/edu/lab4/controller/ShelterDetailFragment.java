package cs2340.gatech.edu.lab4.controller;

/**
 * Created by mike on 2/22/18.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cs2340.gatech.edu.lab4.R;
import cs2340.gatech.edu.lab4.model.Model;
import cs2340.gatech.edu.lab4.model.Shelter;

/**
 * A fragment representing a single Course detail screen.
 *
 * Basically this displays a list of shelter details that are in a particular shelter
 * that was selected from the main screen.
 *
 * This fragment is either contained in a {@link ShelterListActivity}
 * in two-pane mode (on tablets) or a {@link ShelterDetailActivity}
 * on handsets.
 */
public class ShelterDetailFragment extends Fragment {
    /**
     * The fragment arguments representing the  ID's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_COURSE_ID = "course_id";

    /**
     * The course that this detail view is for.
     */
    private Shelter mShelter;

    /**
     * The adapter for the recycle view list of shelter details
     */
    private SimpleDetailsRecyclerViewAdapter adapter;
    private List<String> shelterDetailList;
    private List<String> shelterHeaderList;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShelterDetailFragment() {
    }

    /**
     * Sets up detail fragment for shelter
     * @param savedInstanceState prior state if available
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid course passed to us
        if (getArguments().containsKey(ARG_COURSE_ID)) {
            // Get the id from the intent arguments (bundle) and
            //ask the model to give us the course object
            Model model = Model.getInstance();
            // mShelter = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
            mShelter = model.getCurrentShelter();
            if (mShelter.getDetails() == null) {
                mShelter.setDetails();
                mShelter.setHeaders();
            }
            shelterDetailList = mShelter.getDetails();
            shelterHeaderList = mShelter.getHeaders();
            Log.d("#CourseDetailFragment", "Shelter vacancy: " + mShelter.getAvailableBeds());
//            Log.d("CourseDetailFragment", "Passing over shelter: " + mShelter);
//            Log.d("CourseDetailFragment", "Address: " + mShelter.getAddress());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mShelter.toString());
            }
        }


    }

    /**
     * Called after creation/instantiation of view hierarchy for fragment
     * @param inflater layout inflater
     * @param container ViewGroup object
     * @param savedInstanceState prior state if available, can be NULL
     * @return the View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shelter_detail, container, false);

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = rootView.findViewById(R.id.detail_list);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }

    /**
     * Resumes the fragment
     */
    @Override
    public void onResume() {
        super.onResume();

        Model model = Model.getInstance();
        // mShelter = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
        mShelter = model.getCurrentShelter();
        mShelter.setDetails();
        mShelter.setHeaders();
        shelterDetailList = mShelter.getDetails();
        shelterHeaderList = mShelter.getHeaders();
        adapter.updateData(shelterDetailList);
        Log.d("############", "Details : " + mShelter.getAvailableBeds());
        adapter.notifyDataSetChanged();
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView  the view that needs this adapter
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new SimpleDetailsRecyclerViewAdapter(shelterDetailList,shelterHeaderList);
        Log.d("Adapter", adapter.toString());
        recyclerView.setAdapter(adapter);
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     *
     * In this case, we are just mapping the toString of the shelter details string object to a text field.
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public class SimpleDetailsRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleDetailsRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private List<String> mValues;
        private final List<String> mHeaders;

        public void updateData(List<String> items) {
            mValues = items;
        }

        /**
         * set the items to be used by the adapter
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleDetailsRecyclerViewAdapter(List<String> items,List<String> headers) {
            mValues = items;
            mHeaders = headers;
        }

        @Override
        public SimpleDetailsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*
              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.details_detail, parent, false);
            return new SimpleDetailsRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleDetailsRecyclerViewAdapter.ViewHolder holder, int position) {
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
            //start by getting the element at the correct position
            holder.mDetail = mValues.get(position);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
            holder.mHeader.setText(mHeaders.get(position));
            holder.mContentView.setText(mValues.get(position));
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mHeader;
            public final TextView mContentView;
            public String mDetail;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mHeader = (TextView) view.findViewById(R.id.detail_header);
                mContentView = (TextView) view.findViewById(R.id.shelter_details);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }

        public void setDetails(List<String> items) {
            mValues = items;
        }
    }
}

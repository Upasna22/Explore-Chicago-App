package edu.upasna.cs478.app3_prj3;

import android.content.Context;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RListFragment extends ListFragment {

    private RListFragment.ListSelectionListener mListener = null;
    private static final String TAG = "RListFragment";
    static final String OLD_POSITION = "oldPos" ;
    Integer mOldPosition = null ;

    // Callback interface that allows this Fragment to notify the RestaurantActivity when
    // user clicks on a List Item
    public interface ListSelectionListener {
        public void onListSelection(int index);
    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        // Indicates the selected item has been checked
        // Important: Must have setChoiceMode to either CHOICE_MODE_SINGLE or MULTIPLE
        getListView().setItemChecked(pos, true);

        // Inform the RestaurantActivity that the item in position pos has been selected
        mListener.onListSelection(pos);
    }

    @Override
    public void onAttach(Context activity) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(activity);

        try {

            // Set the ListSelectionListener for communicating with the RestaurantActivity
            mListener = (RListFragment.ListSelectionListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        //TRY
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");
        super.onActivityCreated(savedState);

        // Set the list choice mode to allow only one selection at a time
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Set the list adapter for the ListView
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.activity_rlist_fragment, RestaurantActivity.mrestaurantArray));

         }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");
        View retView =  super.onCreateView(inflater, container, savedState) ;
        if (savedState != null) {
            int oldPosition = savedState.getInt(OLD_POSITION) ;
            Log.i(TAG, "OLD_POSITION = " + oldPosition) ;
            mOldPosition = oldPosition ;
        }
        return retView ;
    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
        if (mOldPosition != null) {
            int oldPosition = mOldPosition ;
            getListView().setSelection(oldPosition) ;
            // Inform the Restaurant Activity that the item in position pos was selected
            mListener.onListSelection(oldPosition);
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onDestroyView()");
        super.onDestroyView();
    }


}

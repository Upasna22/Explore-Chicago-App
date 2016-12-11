package edu.upasna.cs478.app3_prj3;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RPicFragment extends Fragment {

    private static final String TAG = "RPicFragment";
    private ImageView mrestaurantpicView = null;
    private int mCurrIdx = -1;
    private int mrestaurantArrayLen;

    int getShownIndex() {
        return mCurrIdx;
    }

    // Show the Pic string at position newIndex
    void showImageAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= mrestaurantArrayLen)
            return;
        mCurrIdx = newIndex;
        // The imageview should set the image resource as the one selected
        mrestaurantpicView.setImageResource(RestaurantActivity.mRpicArray[mCurrIdx]);
    }
    @Override
    public void onAttach(Context context) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onAttach()");
        super.onAttach(context);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + ":entered onCreate()");
        super.onCreate(savedInstanceState);
        //TRY
        setRetainInstance(true);
    }

    // Called to create the content view for this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onCreateView()");

        // Inflate the layout defined in activity_rpic_fragment.xml
        // The last parameter is false because the returned view does not need to be attached to the container ViewGroup
        return inflater.inflate(R.layout.activity_rpic_fragment, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Log.i(TAG, getClass().getSimpleName() + ":entered onActivityCreated()");

        super.onActivityCreated(savedInstanceState);

        //Get reference to the imageview created in layout
        mrestaurantpicView = (ImageView) getActivity().findViewById(R.id.rpicView);
        mrestaurantArrayLen = RestaurantActivity.mRpicArray.length;

    }

    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + ":entered onStart()");
        super.onStart();
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

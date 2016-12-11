package edu.upasna.cs478.app3_prj3;

//import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import edu.upasna.cs478.app3_prj3.HlistFragment.ListSelectionListener;

public class HotelActivity extends AppCompatActivity implements ListSelectionListener {

    // Arrays used to store Hotel names and images
    public static String[] mHotelArray;
    public static int[] mHpicArray = {R.drawable.image1,
                                        R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image6};


    private FragmentManager mFragmentManager;

    // Creating a layout for the Title fragment and Image fragment
    private FrameLayout mTitleFrameLayout, mImageFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;


    // Keep shown index in activity to save and restore state
    int mShownIndex = -1 ;
    static String OLD_ITEM = "old_item" ;

    // Get a reference to the Hotel PictureFragment
    private final HPicFragment mhpicFragment = new HPicFragment();

    // Create an instance of Hotel ListFragment
    private HlistFragment mlistFragment = null ;

    //TRY



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the string arrays with the hotels names
        mHotelArray = getResources().getStringArray(R.array.Hotels);


        setContentView(R.layout.activity_hotel);

        // Get references of the frames and assign to respective layouts
        mTitleFrameLayout = (FrameLayout) findViewById(R.id.hotel_frame);
        mImageFrameLayout = (FrameLayout) findViewById(R.id.pic_frame);


        // Get a reference to the FragmentManager
        mFragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction =  mFragmentManager
                .beginTransaction();

        // Instance of HListFragment
        mlistFragment = new HlistFragment() ;

        // Add the HListFragment
        fragmentTransaction.replace(R.id.hotel_frame, mlistFragment) ;

        // Commit the FragmentTransaction
        fragmentTransaction.commit();


        // Add a OnBackStackChangedListener to reset the layout when the back stack changes
        mFragmentManager
                .addOnBackStackChangedListener(new android.support.v4.app.FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        setLayout();
                    }
                });


        // Retrieve old state if present
        if (savedInstanceState != null) {
            mShownIndex = savedInstanceState.getInt(OLD_ITEM) ;
    }
        // Setting up the Action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
}



    private void setLayout() {

        // If the configuration is Portrait
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            // Determine whether the HPicFragment has been added
            if (!mhpicFragment.isAdded()) {

                // Make the TitleFragment occupy the entire layout
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));

                // Make the PictureLayout take 2/3's of the layout's width
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            }
        }

        // If the configuration is Landscape

        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Determine whether the HPicFragment has been added
            if (!mhpicFragment.isAdded()) {

                // Make the TitleFragment occupy the entire layout
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        MATCH_PARENT, MATCH_PARENT));
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT));
            } else {

                // Make the TitleLayout take 1/3 of the layout's width
                mTitleFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 1f));

                // Make the PictureLayout take 2/3's of the layout's width
                mImageFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                        MATCH_PARENT, 2f));
            }
        }
    }


    //  Activity about to be visible: Retrieve previous quote, if saved
    //     reset state of titles fragment
    public void onStart() {
        super.onStart() ;
        if (mShownIndex >= 0) {
            mhpicFragment.showImageAtIndex(mShownIndex);
            mlistFragment.setSelection(mShownIndex);
            mlistFragment.getListView().setItemChecked(mShownIndex,true);
        }
    }
    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {

        // If the PictureFragment has not been added, add it now
        if (!mhpicFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the PictureFragment to the layout
            fragmentTransaction.add(R.id.pic_frame,
                    mhpicFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mhpicFragment.getShownIndex() != index) {

            // Tell the PictureFragment to show the picture at position index
            mhpicFragment.showImageAtIndex(index);

        }


        if (mShownIndex != index) {
            // Tell the PictureFragment to show the picture at position index
            mhpicFragment.showImageAtIndex(index);
            // and update the shownIndex
            mShownIndex = index ;
        }
    }
    // Save currently shown quote for later retrieval
    public void onSaveInstanceState(Bundle outState) {
        if (mShownIndex >= 0) {
            outState.putInt(OLD_ITEM, mShownIndex) ;
        }
    }

    // Options Menu

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Inflate the layout
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Switch between two options of menu
        switch (item.getItemId()) {
            case R.id.hotel:
                Intent intent1 = new Intent(HotelActivity.this ,HotelActivity.class);
                startActivity(intent1);
                return true;
            case R.id.restaurant:
                Intent intent2 = new Intent(HotelActivity.this , RestaurantActivity.class);
                startActivity(intent2);
                return true;

            default:
                return false;
        }
    }
}

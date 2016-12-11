package edu.upasna.cs478.app3_prj3;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import edu.upasna.cs478.app3_prj3.RListFragment.ListSelectionListener;

public class RestaurantActivity extends AppCompatActivity implements ListSelectionListener{

    // Arrays used to store Restaurant names and images
    public static String[] mrestaurantArray;
    public static int[] mRpicArray = { R.drawable.image7, R.drawable.image8, R.drawable.image9,R.drawable.image10, R.drawable.image11,R.drawable.image12};


    private FragmentManager mFragmentManager;
    // Creating a layout for the Title fragment and Image fragment
    private FrameLayout mTitleFrameLayout, mImageFrameLayout;

    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;


    // Keep shown index in activity to save and restore state
    int mShownIndex = -1 ;
    static String OLD_ITEM = "old_item" ;

    // Get a reference to the Restaurant PictureFragment
    private final RPicFragment mrpicFragment = new RPicFragment();

    // Create an instance of Restaurant ListFragment
    private RListFragment mlistFragment = null ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // Get the string arrays with the restaurant names
        mrestaurantArray = getResources().getStringArray(R.array.Restaurants);

        setContentView(R.layout.activity_restaurant);

        // Get references of the frames and assign to respective layouts
        mTitleFrameLayout = (FrameLayout) findViewById(R.id.restaurant_frame);
        mImageFrameLayout = (FrameLayout) findViewById(R.id.rpic_frame);

        // Get a reference to the FragmentManager
         mFragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = mFragmentManager
                .beginTransaction();


        // Instance of RListFragment
        mlistFragment = new RListFragment() ;

        // Add the HPicFragment
        fragmentTransaction.replace(R.id.restaurant_frame, mlistFragment);
                //fragmentTransaction.replace(R.id.rpic_frame, mrpicFragment);

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

            // Determine whether the PictureFragment has been added
            if (!mrpicFragment.isAdded()) {

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

            // Determine whether the PictureFragment has been added
            if (!mrpicFragment.isAdded()) {

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


    // Called when the user selects an item in the TitlesFragment
    @Override
    public void onListSelection(int index) {

        // If the PictureFragment has not been added, add it now
        if (!mrpicFragment.isAdded()) {

            // Start a new FragmentTransaction
            FragmentTransaction fragmentTransaction = mFragmentManager
                    .beginTransaction();

            // Add the PictureFragment to the layout
            fragmentTransaction.add(R.id.rpic_frame,
                    mrpicFragment);

            // Add this FragmentTransaction to the backstack
            fragmentTransaction.addToBackStack(null);

            // Commit the FragmentTransaction
            fragmentTransaction.commit();

            // Force Android to execute the committed FragmentTransaction
            mFragmentManager.executePendingTransactions();
        }

        if (mrpicFragment.getShownIndex() != index) {

            // Tell the QuoteFragment to show the quote string at position index
            mrpicFragment.showImageAtIndex(index);

        }


        if (mShownIndex != index) {
            // Tell the PictureFragment to show the image at position index
            mrpicFragment.showImageAtIndex(index);
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

    // Execute tasks onStart
    public void onStart() {
        super.onStart();
        if (mShownIndex >= 0) {
            mrpicFragment.showImageAtIndex(mShownIndex);
            mlistFragment.setSelection(mShownIndex);
            mlistFragment.getListView().setItemChecked(mShownIndex, true);
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
                Intent intent1 = new Intent(RestaurantActivity.this ,HotelActivity.class);
                startActivity(intent1);
                return true;
            case R.id.restaurant:
                Intent intent2 = new Intent(RestaurantActivity.this , RestaurantActivity.class);
                startActivity(intent2);
                return true;

            default:
                return false;
        }
    }
}

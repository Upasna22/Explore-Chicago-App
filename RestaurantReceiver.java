package edu.upasna.cs478.app3_prj3;

import android.content.BroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class RestaurantReceiver extends BroadcastReceiver {

    public void onReceive(Context arg0, Intent arg1) {

        Intent intent1 = new Intent(arg0,RestaurantActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        arg0.startActivity(intent1);
    }
}

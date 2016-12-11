package edu.upasna.cs478.app2_prj3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReceiver1 extends BroadcastReceiver {

    // OnReceive method receives the intent that was broadcast ;takes context and argument as parameters
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        Toast.makeText(arg0, "Hotel in action! ",
                Toast.LENGTH_LONG).show() ;

    }

}

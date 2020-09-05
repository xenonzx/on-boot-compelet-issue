package com.ahmed.eldakhakhny.labs.bootcomplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmRebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context oContext, Intent intent) {
        Log.d("AlarmRebootReceiver", "Event Received");
        // 1 you can start a new activity on api 24 devices
        oContext.startActivity(new Intent(oContext,MainActivity.class));
    }
}
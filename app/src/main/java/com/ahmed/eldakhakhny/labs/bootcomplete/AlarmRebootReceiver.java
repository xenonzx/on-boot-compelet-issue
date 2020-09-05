package com.ahmed.eldakhakhny.labs.bootcomplete;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.flutter.embedding.android.FlutterActivity;

public class AlarmRebootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context oContext, Intent intent) {
        Log.d("AlarmRebootReceiver", "Event Received");
        // 1 you can start a new activity on api 24 devices
        //oContext.startActivity(new Intent(oContext,MainActivity.class));

        // 2 starting flutter activity from broadcast receiver on api 24
        // the  flutter code is added as a dependency to the android project
        oContext.startActivity(FlutterActivity.createDefaultIntent(oContext));
    }
}
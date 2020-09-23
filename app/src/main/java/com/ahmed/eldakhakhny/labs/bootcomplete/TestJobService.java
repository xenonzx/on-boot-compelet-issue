package com.ahmed.eldakhakhny.labs.bootcomplete;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

import io.flutter.embedding.android.FlutterActivity;

public class TestJobService extends JobService {
    private static final String TAG = "SyncService";
    public static final String CHANNEL_DAKHAKHNY = "channed_dakhakhny";

    private String CHANNEL_ID ="dakhakhny ID";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("TestJobService","onStartJob");
        startFlutterActivity();
        Log.e("TestJobService","your code shall go here");
        // make sure that you return false
        return false;
    }

    private void startFlutterActivity() {
        Intent i = FlutterActivity.createDefaultIntent(getBaseContext());
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getBaseContext().startActivity(i);
    }

    @Override
    public void onCreate() {
        Log.e("TestJobService","onCreate");
        super.onCreate();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TestJobService","onUnbind");
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("TestJobService","onRebind");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TestJobService","onDestroy");
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("TestJobService","onStopJob");
        return true;
    }
}
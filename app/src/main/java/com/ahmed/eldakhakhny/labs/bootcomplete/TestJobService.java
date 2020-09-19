package com.ahmed.eldakhakhny.labs.bootcomplete;

import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.ahmed.eldakhakhny.softexpert.bootcomplete.R;

import io.flutter.embedding.android.FlutterActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static androidx.core.app.NotificationCompat.*;

public class TestJobService extends JobService {
    private static final String TAG = "SyncService";

    private String CHANNEL_ID ="dakhakhny channel";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.v("TestJobService","onStartJob");

        showNotification();
        return true;
    }

    private void showNotification() {

        Log.v("TestJobService","showNotification");
        Builder builder = new Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("boot complete")
                .setContentText("this is a test notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.v("TestJobService","onStopJob");
        return true;
    }

}
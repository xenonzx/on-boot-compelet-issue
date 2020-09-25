package com.ahmed.eldakhakhny.labs.bootcomplete;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.ahmed.eldakhakhny.softexpert.bootcomplete.R;
import io.flutter.embedding.android.FlutterActivity;

public class TestJobService extends JobService {
    private static final String TAG = "SyncService";
    public static final String CHANNEL_DAKHAKHNY = "channed_dakhakhny";

    private String CHANNEL_ID ="dakhakhny ID";
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("TestJobService","onStartJob");
        //startFlutterActivity();
        createNotificationChannel();
        showNotification();
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

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Log.v("TestJobService","showNotification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("boot complete")
                .setContentText("this is a test notification from eldakhakhny")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_DAKHAKHNY;
            String description = "desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
package com.ahmed.eldakhakhny.labs.bootcomplete

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.ahmed.eldakhakhny.softexpert.bootcomplete.R

class TestForegroundService: Service() {
    companion object {
        val TAG = "TestForegroundService"
        val CHANNEL_DAKHAKHNY = "channed_dakhakhny"
        val ONGOING_NOTIFICATION_ID = 1
        private val CHANNEL_ID = "dakhakhny ID"
        val DELAY_IN_MILLIS: Long = 3000
        val STEPS = "steps"
    }

    override fun onCreate() {
        // this method should be called once
        Log.v(TAG,"onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // called every start service
        Log.v(TAG,"onStartCommand")
        createNotificationChannel()
        val notification = createNotification()

        startForeground(ONGOING_NOTIFICATION_ID, notification)
        // start long running task on background thread
        doSomeBackgroundWork()

        return START_NOT_STICKY
    }

    //TODO replace ur work here
    private fun doSomeBackgroundWork() {
        // your steps counting work shall go here
        val steps =  PreferenceManager.getDefaultSharedPreferences(applicationContext).getInt(STEPS,0)
        simulateStepsCounting(steps)
    }


    private fun simulateStepsCounting(steps: Int) {
        var steps1 = steps
        Handler().postDelayed({
            Log.v(TAG, "step ${steps1++}")
            simulateStepsCounting(steps1)
            PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().putInt(STEPS,steps1).commit()
        }, DELAY_IN_MILLIS)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.v(TAG,"onBind")
        // use binder to communicate with starting activity
        return null
    }

    private fun createNotification(): Notification {
        Log.v(TAG,"createNotification")
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            notificationIntent, 0
        )
        Log.v("TestJobService", "showNotification")
        val builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("foreground service")
                .setContentText("this is a test notification from foreground")
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_LOW)
        return builder.build()
    }

    private fun createNotificationChannel() {
        Log.v(TAG,"createNotificationChannel")
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = TestJobService.CHANNEL_DAKHAKHNY
            val description = "desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}
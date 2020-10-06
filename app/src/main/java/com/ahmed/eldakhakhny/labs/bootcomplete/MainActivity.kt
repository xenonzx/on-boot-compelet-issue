package com.ahmed.eldakhakhny.labs.bootcomplete

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ahmed.eldakhakhny.softexpert.bootcomplete.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        startForegroundService()
    }

    private fun startForegroundService() {
        val serviceIntent = Intent(this, TestForegroundService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
    }
    private fun initUI(){
        val steps = PreferenceManager.getDefaultSharedPreferences(applicationContext)
            .getInt(TestForegroundService.STEPS, 0);
        stepsTV.text = "steps $steps"
    }

}
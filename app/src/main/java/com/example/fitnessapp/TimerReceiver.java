package com.example.fitnessapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimerReceiver extends BroadcastReceiver {
    private static final String TAG = "TimerReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Timer triggered");
        // Put your timer logic here
        // For demonstration, we'll just print a log message
    }
}
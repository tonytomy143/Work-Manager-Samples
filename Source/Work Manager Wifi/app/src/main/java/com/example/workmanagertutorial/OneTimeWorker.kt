package com.example.workmanagertutorial

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class OneTimeWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val TAG = "OneTimeWorker"


    override fun doWork(): Result {
        ContextCompat.getMainExecutor(applicationContext).execute {
            Log.d(TAG, "WorkManager started....")
            showToast("WorkManager started....")

            val wifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            when (wifiManager.isWifiEnabled) {
                true  -> showToast("Wifi enabled")
                false -> showToast("Wifi disabled")
            }
        }

        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

    fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}
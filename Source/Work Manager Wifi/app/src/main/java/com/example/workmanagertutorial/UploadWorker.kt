package com.example.workmanagertutorial

import android.content.Context
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val TAG = "UploadWorker"


    override fun doWork(): Result {

        val appContext = applicationContext

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)

        makeStatusNotification("Notification Time $current", appContext)

        // Do the work here--in this case, upload the images.
//        uploadImages()
        /*ContextCompat.getMainExecutor(applicationContext).execute {
            Log.d(TAG, "WorkManager started....")
            showToast("WorkManager started....")

            val wifiManager =
                applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            when (wifiManager.isWifiEnabled) {
                true  -> showToast("Wifi enabled")
                false -> showToast("Wifi disabled")
            }
        }*/

        // Indicate whether the work finished successfully with the Result
        return Result.success()

    }

    fun showToast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}
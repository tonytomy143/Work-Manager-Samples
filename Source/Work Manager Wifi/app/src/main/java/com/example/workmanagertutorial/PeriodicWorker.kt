package com.example.workmanagertutorial

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.*

class PeriodicWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    private val TAG = "PeriodicWorker"


    override fun doWork(): Result {

        val appContext = applicationContext

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)

        makeStatusNotification("Notification Time $current", appContext)

        // Indicate whether the work finished successfully with the Result
        return Result.success()

    }

}
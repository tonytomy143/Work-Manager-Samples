package com.example.workmanagertutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.workmanagertutorial.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var uploadWorkRequest: WorkRequest
    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(this)
//        uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()

        uploadWorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(
            MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        ).build() // Min Interval should be 15 min


        binding.btnStartWorker.setOnClickListener {
            workManager.enqueue(uploadWorkRequest)
            binding.tvWorkerStatus.text = "Work Manager executed!"
        }

        workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this) {
            if (it != null) {
                val state = it.state
                binding.tvWorkerStatus.append(state.toString() + "\n")
            }
        }

    }
}

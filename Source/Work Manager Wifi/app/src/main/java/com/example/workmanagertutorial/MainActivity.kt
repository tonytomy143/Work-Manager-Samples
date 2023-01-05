package com.example.workmanagertutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.workmanagertutorial.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var oneTimeRequest: WorkRequest
    private lateinit var periodicRequest: WorkRequest
    private lateinit var workManager: WorkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        workManager = WorkManager.getInstance(this)
        oneTimeRequest = OneTimeWorkRequestBuilder<OneTimeWorker>().build()

        periodicRequest = PeriodicWorkRequestBuilder<PeriodicWorker>(
            MIN_PERIODIC_INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        ).build() // Min Interval should be 15 min


        binding.btnOneTimeWork.setOnClickListener {
            workManager.enqueue(oneTimeRequest)
            binding.tvOneTimeStatus.text = "One Time Request Executed!"
        }

        binding.btnPeriodicWork.setOnClickListener {
            workManager.enqueue(periodicRequest)
            binding.tvPeriodicStatus.text = "Periodic Request Executed!"
        }

        binding.btnCancelWork.setOnClickListener {
            workManager.cancelAllWork()
        }

        workManager.getWorkInfoByIdLiveData(oneTimeRequest.id).observe(this) {
            if (it != null) {
                val state = it.state
                binding.tvOneTimeStatus.append("$state\n")
            }
        }

        workManager.getWorkInfoByIdLiveData(periodicRequest.id).observe(this) {
            if (it != null) {
                val state = it.state
                binding.tvPeriodicStatus.append("$state\n")
            }
        }

    }
}

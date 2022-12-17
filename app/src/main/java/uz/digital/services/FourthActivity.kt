package uz.digital.services

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import uz.digital.services.databinding.ActivityFourthBinding
import uz.digital.services.services.MyJobService

class FourthActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFourthBinding.inflate(layoutInflater) }
    private val TAG = "MyJobService"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(100)
            Log.d(TAG, "onCreate: Job cancelled")
        }
        binding.button4.setOnClickListener {
            val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val componentName = ComponentName(this, MyJobService::class.java)
            val jobInfo = JobInfo.Builder(100, componentName)
                .setRequiresCharging(true)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(15 * 60 * 1000)
                .build()
            val int = jobScheduler.schedule(jobInfo)
            if (int == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "onCreate: Job Scheduled")
            } else {
                Log.d(TAG, "onCreate: Job scheduling failed")
            }
        }
    }
}
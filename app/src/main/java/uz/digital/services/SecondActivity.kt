package uz.digital.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.digital.services.databinding.ActivitySecondBinding
import uz.digital.services.services.MyForegroundService

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.start.setOnClickListener {
            startService(Intent(this, MyForegroundService::class.java))
            binding.textView.text = "Service started"
        }
        binding.stop.setOnClickListener {
            stopService(Intent(this, MyForegroundService::class.java))
            binding.textView.text = "Service stopped"
        }
    }
}
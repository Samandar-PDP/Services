package uz.digital.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.digital.services.databinding.ActivityMainBinding
import uz.digital.services.services.MyBackgroundService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            stopService(Intent(this, MyBackgroundService::class.java))
        }
        binding.button2.setOnClickListener {
            val url = "kfjdlfj"
            val intent = Intent(this, MyBackgroundService::class.java)
            intent.putExtra("Url", url)
            startService(intent)
        }
    }
}
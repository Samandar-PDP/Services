package uz.digital.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uz.digital.services.databinding.ActivityThirdBinding
import uz.digital.services.services.MyBoundService

class ThirdActivity : AppCompatActivity() {
    private lateinit var myBoundService: MyBoundService
    private var isBound = false
    private val binding by lazy { ActivityThirdBinding.inflate(layoutInflater) }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                myBoundService.getProgress().collect {
                    binding.textView.text = it.toString()
                    binding.progressBar.progress = it

                    if (it == 100) {
                        binding.textView.text = "Finished"
                        binding.progressBar.progress = 1
                    }
                }
            }
        }
    }
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val service = binder as MyBoundService.MyBinder
            myBoundService = service.getMyBoundService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MyBoundService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
}
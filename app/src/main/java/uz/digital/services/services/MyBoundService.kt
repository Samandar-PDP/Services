package uz.digital.services.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyBoundService : Service() {
    private val TAG = "MyBoundService"
    private val binder = MyBinder()
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    fun getProgress(): Flow<Int> {
        var count = 0
        return flow {
            while (count < 100) {
                delay(500L)
                count += 1
                emit(count)
            }
        }
    }

    inner class MyBinder : Binder() {
        fun getMyBoundService() = this@MyBoundService
    }
}
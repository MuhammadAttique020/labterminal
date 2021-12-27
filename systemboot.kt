package com.example.recyleview

class RunServiceOnBoot : Service() {
    private val TAG = "MyService"
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val runTime = 5000
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        Log.i(TAG, "onCreate")
        handler = Handler()
        runnable = Runnable { handler.postDelayed(runnable, runTime.toLong()) }
        handler.post(runnable)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.i(TAG, "onStart")
    }
}

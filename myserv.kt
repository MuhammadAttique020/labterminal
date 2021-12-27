package com.example.recyleview
class MyService : servic() {

    val TAG ="MyService"

    override fun onBind(intent: Intent): IBinder?=null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val datastring= intent?.getStringExtra("ExtraData")
        datastring?.let{
            Log.d(TAG,datastring)
        }
        Thread{
            while (true){}
        }.start()
        Log.d(TAG,"Service has been Running")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service has been Killed")
    }
}
package com.example.recyleview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.new_layout.*
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    var edi :String = "1234"
    var edi2:String="hello"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reccyle.layoutManager=LinearLayoutManager(this)
        reccyle.adapter= customadapter()
        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.RECEIVE_SMS,android.Manifest.permission.SEND_SMS),
                111
            )
        }
        else{
            reciveMsg()
        }
        btn2.setOnClickListener{
            var sms = SmsManager.getDefault()
            sms.sendTextMessage(edi,"ME",edi2,null,null)
        }
        btnser.setOnClickListener{
            val intent = Intent(this,servic::class.java)
            startActivity(intent)
        }



    }
    private lateinit var binding: ReultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ReultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            reciveMsg()
        }
    }

    private fun reciveMsg() {
        val br = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for (sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        Toast.makeText(applicationContext,sms.displayMessageBody,Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent1 = Intent(this@MainActivity, RunServiceOnBoot::class.java)
        startService(intent1)
    }

}
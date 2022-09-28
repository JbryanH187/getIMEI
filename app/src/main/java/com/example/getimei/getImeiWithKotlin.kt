package com.example.getimei

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class getImeiWithKotlin: AppCompatActivity() {
    var tm: TelephonyManager? = null
    var imeitxt: TextView? = null
    var imeibtn: Button? = null
    var imei: String? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imeitxt = findViewById<View>(R.id.imei) as TextView?
        imeibtn = findViewById<View>(R.id.getimeibtn) as Button?
        val permisI = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        if (permisI == PackageManager.PERMISSION_GRANTED) {
            tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
            imei = tm!!.deviceId.toString()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                123
            )
        }
        imeibtn!!.setOnClickListener { imeitxt!!.text = imei }
    }
}
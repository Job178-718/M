package com.sun.m

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.sun.m.base.BaseActivity
import com.sun.m.databinding.ActivityMainBinding
import com.sun.m.service.WebService
import com.sun.m.utils.NetworkUtils
import com.sun.m.utils.startService


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService<WebService>()
        Log.d("MainActivity", "onCreate: ${NetworkUtils.getLocalIPAddress()}")
    }




}
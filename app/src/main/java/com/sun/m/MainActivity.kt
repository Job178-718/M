package com.sun.m

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewbinding.ViewBinding
import com.sun.m.base.BaseActivity
import com.sun.m.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //替换setContentView
        ActivityMainBinding.inflate(layoutInflater)
    }




}
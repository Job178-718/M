package com.sun.m.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.sun.m.R
import com.sun.m.utils.ScreenUtils

open class BaseActivity<VB:ViewBinding>: AppCompatActivity() {


    lateinit var VB:ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //全屏展示
        fullScreen()
    }



    private fun fullScreen() {
        ScreenUtils.setNavigationBarColor(this,resources.getColor(R.color.background))
        ScreenUtils.setStatusBarColor(this,resources.getColor(R.color.background))
        ScreenUtils.setStatusBarDarkFont(this,true)
        ScreenUtils.setNavigationBarFont(this,true)
    }
}
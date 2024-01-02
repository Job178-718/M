package com.sun.m.base

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import java.lang.Appendable

open class BaseViewMode<T: Activity>(application: Application):AndroidViewModel(application){

    lateinit var activity:T

    fun setIActivity(activity:T){
        this.activity = activity
    }

}
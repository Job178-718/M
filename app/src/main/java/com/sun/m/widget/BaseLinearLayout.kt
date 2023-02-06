package com.sun.m.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.sun.m.R

class BaseLinearLayout:LinearLayout {

    constructor(context: Context):super(context){
        BaseLinearLayout(context,null)
    }
    constructor(context: Context, attributeSet: AttributeSet?):super(context,attributeSet){
       BaseLinearLayout(context,attributeSet!!,0)
    }
    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int):super(context,attributeSet,defStyle){
        LayoutInflater.from(context).inflate(R.layout.base,this)
    }
}
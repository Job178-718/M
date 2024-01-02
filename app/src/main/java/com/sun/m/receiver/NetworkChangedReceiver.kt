package com.sun.m.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.util.EventListener

/**
 * 广播注册模板
 */
class NetworkChangedReceiver: BroadcastReceiver() {

    var eventListener:ReceiverEventCallBack?=null

    fun setEventReceiverListener(eventListener: ReceiverEventCallBack){
        this.eventListener = eventListener
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        eventListener!!.receiverEvent(intent!!,context!!)
    }

    open interface ReceiverEventCallBack{
        fun receiverEvent(intent:Intent,context:Context)
    }


}
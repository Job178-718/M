package com.sun.m.viewmode

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.LinkProperties
import android.net.Network
import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sun.m.MainActivity
import com.sun.m.base.BaseViewMode
import com.sun.m.receiver.NetworkChangedReceiver
import com.sun.m.service.WebService
import com.sun.m.utils.NetworkUtils
import com.sun.m.utils.startService
import fi.iki.elonen.NanoHTTPD

class FileViewModel(application: Application): BaseViewMode<MainActivity>(application) {

    val ipLiveData = MutableLiveData<Boolean>(false)

    fun startServices() {
        activity.startService<WebService>()
    }

    fun saveFile(session: NanoHTTPD.IHTTPSession) {
        
    }

    fun registerReceiver(){
        //网络变化监听器
       // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
        if (false){
//            val mConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
//            val networkCallbackImpl = NetworkCallbackImpl()
//            mConnectivityManager.requestNetwork(NetworkRequest.Builder().build(),networkCallbackImpl)
        }else{
            val networkReceiver = NetworkChangedReceiver()
            networkReceiver.setEventReceiverListener(object : NetworkChangedReceiver.ReceiverEventCallBack{
                override fun receiverEvent( intent: Intent, context: Context) {
                    Log.d("FileViewModel", "receiverEvent: ${intent.action.toString()}")
                    Log.d("FileViewModel", "receiverEvent: ${intent.type}")
                    Log.d("FileViewModel", "receiverEvent: ${intent.data}")
                    val netWorkInfo = NetworkUtils.getNetWorkInfo(context)
                    if(netWorkInfo==1||netWorkInfo==0){
                       ipLiveData.postValue(true)
                    }else{
                       ipLiveData.postValue(false)
                    }
                }
            })

            val netFilter = IntentFilter()
            netFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
            netFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
            netFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
            activity.registerReceiver(networkReceiver,netFilter)
        }
    }

    inner class NetworkCallbackImpl: NetworkCallback() {
        override fun onBlockedStatusChanged(network: Network, blocked: Boolean) {
            super.onBlockedStatusChanged(network, blocked)
            Log.d("FileViewModel", "onBlockedStatusChanged: 1233")
        }

        override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
            super.onLinkPropertiesChanged(network, linkProperties)
            Log.d("FileViewModel", "onLinkPropertiesChanged: ")
        }

    }

}
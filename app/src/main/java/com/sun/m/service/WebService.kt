package com.sun.m.service

import android.content.Context
import android.content.Intent
import android.os.PowerManager
import android.util.Log
import com.sun.m.Dao.FileDao
import com.sun.m.MApplication
import com.sun.m.base.BaseService
import com.sun.m.receiver.NetworkChangedListener
import com.sun.m.utils.NetworkUtils
import com.sun.m.web.HttpServer
import io.legado.app.web.WebSocketServer
import splitties.systemservices.powerManager
import java.io.IOException

class WebService : BaseService() {

    lateinit var httpServer:HttpServer

    lateinit var webSocketServer:WebSocketServer


    private val wakeLock: PowerManager.WakeLock by lazy {
        powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "legado:webService")
            .apply {
                setReferenceCounted(false)
            }
    }
    private val networkChangedListener by lazy {
        NetworkChangedListener(this)
    }

    override fun onCreate() {
        super.onCreate()
        networkChangedListener.register()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        upWebServer()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        networkChangedListener.unRegister()
    }

    private fun upWebServer() {
        val address = NetworkUtils.getLocalIPAddress()
        if (address != null) {
            val port = 1222
            httpServer = HttpServer(port)
            webSocketServer = WebSocketServer(port + 1)
            try {
                httpServer?.start()
                webSocketServer?.start(1000 * 30) // 通信超时设置
            } catch (e: IOException) {
                stopSelf()
            }
        } else {
            stopSelf()
        }

    }
}

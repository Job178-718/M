package com.sun.m.viewmode

import android.app.Application
import android.app.Service
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.sun.m.service.WebService
import com.sun.m.utils.startService
import fi.iki.elonen.NanoHTTPD

class FileViewModel(application: Application): AndroidViewModel(application) {
    fun startServices(context: Context) {
        context.startService<WebService>()
    }

    fun saveFile(session: NanoHTTPD.IHTTPSession) {
        
    }
}
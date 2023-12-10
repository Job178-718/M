package com.sun.m.Dao

import android.content.Context
import com.sun.m.utils.logd
import fi.iki.elonen.NanoHTTPD
import java.io.File

class FileDao {


    lateinit var context: Context
    private constructor(context: Context){
        this.context = context
    }

    companion object{

        var fileDao:FileDao? = null
        @JvmStatic
        fun getInstance(context: Context): FileDao {
            if(fileDao == null){
                fileDao = FileDao(context)
            }
            return fileDao!!
        }

    }




}
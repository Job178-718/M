package com.sun.m.web


import android.os.Build
import android.util.Log
import com.sun.m.utils.AssetsWeb
import com.sun.m.utils.ResponseStatus
import fi.iki.elonen.NanoHTTPD
import java.io.IOException
import kotlin.collections.set


class HttpServer : NanoHTTPD {

    private val assetsWeb = AssetsWeb("web")

    constructor(port: Int) : super(port){

    }

    override fun serve(session: IHTTPSession): Response? {
        var returnData: ResponseStatus? = null
        val ct = ContentType(session.headers["content-type"]).tryUTF8()
        session.headers["content-type"] = ct.contentTypeHeader

        var uri = session.uri
        try {
            when (session.method) {
                Method.OPTIONS -> {
                    val response = newFixedLengthResponse("")
                    response.addHeader("Access-Control-Allow-Methods", "POST")
                    response.addHeader("Access-Control-Allow-Headers", "content-type")
                    response.addHeader("Access-Control-Allow-Origin", session.headers["origin"])
                    //response.addHeader("Access-Control-Max-Age", "3600");
                    return response
                }

                Method.POST -> {
                    when(uri){
                        "/upload/file" -> {
                            saveDate(session)
                        }
                    }
                }

                Method.GET -> {
                }

                else -> Unit
            }

            if (returnData == null) {
                if (uri.endsWith("/"))
                    uri += "index.html"
            }
            return assetsWeb.getResponse(uri)
        } catch (e: Exception) {
            return newFixedLengthResponse(e.message)
        }

    }

    private fun saveDate(session: NanoHTTPD.IHTTPSession) {
        Log.d("${this::class.java}"," ${session.parameters}")
        Log.d("${this::class.java}"," ${session.inputStream.toString()}")
        Log.d("${this::class.java}"," ${session.parameters.entries}")
        Log.d("${this::class.java}"," ${session.parameters.size}")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d("${this::class.java}"," ${session.headers}")
        }
        Log.d("${this::class.java}"," ${session.parms}")

        val params: Map<String, String> = HashMap()
        try {
            session.parseBody(params)
            val fileName = params["fileName"]
            // 在这里处理fileName参数
            println("Received fileName: $fileName")
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ResponseException) {
            e.printStackTrace()
        }
    }

}

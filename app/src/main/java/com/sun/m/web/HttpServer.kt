package com.sun.m.web

import com.sun.m.api.ReturnData
import com.sun.m.utils.AssetsWeb
import fi.iki.elonen.NanoHTTPD
import kotlin.collections.set

class HttpServer(port: Int) : NanoHTTPD(port) {

    private val assetsWeb = AssetsWeb("web")
    override fun serve(session: IHTTPSession): Response? {
        var returnData: ReturnData? = null

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

                }

                Method.GET -> {
                }

                else -> Unit
            }

            if (returnData == null) {
                if (uri.endsWith("/"))
                    uri += "index.html"
                return assetsWeb.getResponse(uri)
            }
            return null
        } catch (e: Exception) {
            return newFixedLengthResponse(e.message)
        }

    }

}

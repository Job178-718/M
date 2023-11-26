package io.legado.app.web

import fi.iki.elonen.NanoWSD

class WebSocketServer(port: Int) : NanoWSD(port) {

    override fun openWebSocket(handshake: IHTTPSession): WebSocket? {

        return when (handshake.uri) {
            else -> null
        }
    }

    override fun serve(session: IHTTPSession?): Response {
        return super.serve(session)
    }

}

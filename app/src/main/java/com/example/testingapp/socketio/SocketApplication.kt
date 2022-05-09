package com.example.testingapp.socketio

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketApplication {
    companion object{
        private lateinit var socket : Socket

        fun get(): Socket {
            try {
                socket = IO.socket("[uri]")
            }catch (e: URISyntaxException){

            }
            return socket
        }

    }
}
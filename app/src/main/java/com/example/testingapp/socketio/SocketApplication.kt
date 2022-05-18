package com.example.testingapp.socketio

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class SocketApplication {
    companion object{
        private lateinit var socket : Socket

        fun get(): Socket {
            try {
                socket = IO.socket("http://10.1.8.184:8080")
            }catch (e: URISyntaxException){
                Log.e("ERR",e.toString())
            }
            return socket
        }

    }
}
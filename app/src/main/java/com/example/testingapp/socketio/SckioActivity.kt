package com.example.testingapp.socketio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.testingapp.R
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_sckio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.json.JSONObject

class SckioActivity : AppCompatActivity() {

    lateinit var mSocket: Socket
    lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sckio)

//         mSocket = SocketApplication.get()
//        mSocket.connect()

        val editText :EditText = findViewById(R.id.edt_text01)
        val btnText :Button = findViewById(R.id.btn_text01)
        btnText.setOnClickListener {
//            mSocket.emit("message","hello")
            Log.d("send socket11",editText.text.toString())
        }

//        mSocket.on("get message",onMessage)

    }


      var onMessage = Emitter.Listener {
        args ->
        val sendText :TextView = findViewById(R.id.tv_text01)
        val obj = JSONObject(args[0].toString())
        val a = sendText.text.toString()
        Log.d("main activity",obj.toString())
//        scope = CoroutineScope(GlobalScope.coroutineContext)
        scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            runOnUiThread {
                kotlin.run {
                    sendText.text = a + "\n" + obj.get("name") + ": " + obj.get("message")
                }
            }
        }.start()


    }
}
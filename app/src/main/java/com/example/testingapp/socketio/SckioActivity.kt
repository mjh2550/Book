package com.example.testingapp.socketio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.testingapp.R
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.android.synthetic.main.activity_sckio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import org.json.JSONObject
import java.net.URISyntaxException

class SckioActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var mSocket: Socket
    lateinit var scope: CoroutineScope
    lateinit var sendText : TextView
    lateinit var editText : EditText
    lateinit var btnText  : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sckio)

        try {
            mSocket = IO.socket("http://192.168.89.149:3000")
            mSocket.connect()
            Log.d("Connected","OK")
        }catch (e: URISyntaxException){
            Log.d("ERR",e.toString())
        }

        editText = findViewById(R.id.edt_text01)
        btnText = findViewById(R.id.btn_text01)
        sendText = findViewById(R.id.tv_text01)
        btnText.setOnClickListener(this)

//        mSocket.on("get message",onMessage)
        mSocket.on(Socket.EVENT_CONNECT,onConnect)
    }


      val onConnect = Emitter.Listener {
//          mSocket.emit("emitReceive","OK")
//          Log.d("emit","ing....")
        args ->
        val obj = JSONObject(args[0].toString())
        val a = sendText.text.toString()
        Log.d("main activity",obj.toString())
          Thread(object : Runnable{
              override fun run() {
                  runOnUiThread(Runnable {
                      kotlin.run {
                          sendText.text = a + "\n" + obj.get("name") + ": " + obj.get("message")
                      }
                  })
              }
          }).start()


//        scope = CoroutineScope(GlobalScope.coroutineContext)
       /* scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            runOnUiThread {
                kotlin.run {
                    sendText.text = a + "\n" + obj.get("name") + ": " + obj.get("message")
                }
            }
        }.start()*/

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_text01){
            mSocket.emit("message","hello")
            Log.d("send socket11",editText.text.toString())
            editText.text.clear()
        }

    }
}
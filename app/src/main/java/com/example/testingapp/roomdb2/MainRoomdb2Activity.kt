package com.example.testingapp.roomdb2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testingapp.R

class MainRoomdb2Activity : AppCompatActivity() {
    private var catDb : CatDB? = null
    private var catList = listOf<Cat>()
    lateinit var mRecyclerView : RecyclerView
    lateinit var mAddBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_roomdb2)
        mRecyclerView = findViewById(R.id.mRecyclerView)
        mAddBtn = findViewById(R.id.mAddBtn)

        catDb = CatDB.getInstance(this)

        val r = Runnable {
            // 데이터에 읽고 쓸때는 쓰레드 사용
            try {
                catList = catDb?.catDao()?.getAll()!!
                var mAdapter = CatAdapter(this,catList)
                mAdapter.notifyDataSetChanged()

                mRecyclerView.adapter = mAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(this)
                mRecyclerView.setHasFixedSize(true)
            }catch (e:Exception){
                Log.e("Error",e.message.toString())
            }
        }

        val thread = Thread(r)
        thread.start()

        mAddBtn.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onDestroy() {
        CatDB.destroyInstance()
        catDb = null
        super.onDestroy()

    }
}
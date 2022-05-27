package com.example.testingapp.contextMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.testingapp.R
import kotlinx.android.synthetic.main.view_items.*

class MainContextMenuActivity : AppCompatActivity() {
    private lateinit var text0101 : TextView
    private lateinit var list1 : ListView

    val data1 = arrayOf(
        "항목1","항목2","항목3","항목4","항목5"

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_context_menu)

        text0101 = findViewById(R.id.tv_0101)
        list1 = findViewById(R.id.list1)

        //android의 기본 레이아웃 어댑터로 등록
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,data1)
        list1.adapter = adapter

        //ContextMenu를 View에 등록
        registerForContextMenu(text0101)
        registerForContextMenu(list1)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)


        //길게 누른 View의 id로 분기
        when(v?.id){
            R.id.tv_0101 ->{
                menu?.setHeaderTitle("tv의 메뉴")
                menuInflater.inflate(R.menu.main_context_menu,menu)
            }
            R.id.list1 ->{

                //menuinfo에는 사용자가 길게누른 항목 인덱스 번호를 가져온다.

                val info = menuInfo as AdapterView.AdapterContextMenuInfo //형변환


                menu?.setHeaderTitle("lv의 메뉴 : ${info.position}")
                menuInflater.inflate(R.menu.list_items,menu)
            }
        }


    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        //텍스트뷰가 아닌 리스트 뷰 일 때만 포지션을 받아옴
        when (item.itemId) {
            R.id.litem1, R.id.litem2, R.id.litem3 -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                Toast.makeText(this, "${info.position}", Toast.LENGTH_SHORT).show()
            }
        }
        //메뉴의 id 값으로 분기
        when(item.itemId){
            R.id.ctext01 ->{Toast.makeText(this, "tv1", Toast.LENGTH_SHORT).show()}
            R.id.ctext02 ->{Toast.makeText(this, "tv2", Toast.LENGTH_SHORT).show()}
        }

        return super.onContextItemSelected(item)
    }
}
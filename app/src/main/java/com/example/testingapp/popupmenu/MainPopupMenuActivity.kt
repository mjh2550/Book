package com.example.testingapp.popupmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.testingapp.R
import kotlinx.android.synthetic.main.activity_main_popup_menu.*

class MainPopupMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_popup_menu)

        button001.setOnClickListener{
            //popupMenu 객체를 생성한다.
            val pop = PopupMenu(this,textView3)

            //메뉴
            menuInflater.inflate(R.menu.main_popup_items,pop.menu)

            pop.setOnMenuItemClickListener{
                //menuitem에는 인덱스번호가 들어옴
                when(it.itemId){
                    R.id.pitem1 -> {
                        textView3.text = "메뉴1을눌렀습니다."
                    }
                    R.id.pitem2 -> {
                        textView3.text = "메뉴2을눌렀습니다."
                    }
                    R.id.pitem3 -> {
                        textView3.text = "메뉴3을눌렀습니다."
                    }
                }
                true
            }

            pop.show()
        }
    }
}
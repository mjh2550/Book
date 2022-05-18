package com.example.testingapp.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.testingapp.R
import kotlinx.android.synthetic.main.activity_permission.*
import java.util.jar.Manifest

class PermissionActivity : AppCompatActivity() {

    /**
     * 퍼미션 허용받기
     */

    val permission_list = arrayOf(
        android.Manifest.permission.INTERNET,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.READ_CONTACTS,
        android.Manifest.permission.WRITE_CONTACTS,
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        tvtv.text = ""

        for(permission in permission_list){

            val chk = checkCallingOrSelfPermission(permission)

            when(chk){
                PackageManager.PERMISSION_GRANTED -> tvtv.append("$permission : 허용\n" )
                PackageManager.PERMISSION_DENIED -> tvtv.append("$permission : 거부\n" )

            }
        }

        btnbtn.setOnClickListener{
            //거부권한들 사용자에게 확인받는다.
            requestPermissions(permission_list,0)
        }


    }

    //사용자에게 허용 여부를 확인받으면 자동으로 호출되는 메서드
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        tvtv.text = ""

        for(idx in grantResults.indices){
            when(grantResults[idx]){
                PackageManager.PERMISSION_GRANTED -> tvtv.append("${permissions[idx]} : 허용 ")
                PackageManager.PERMISSION_DENIED -> tvtv.append("${permissions[idx]} : 거부 ")
            }
        }
    }

    //옵션메뉴 활성화//TF
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //XML 구성
//        menuInflater.inflate(R.menu.main_menu, menu)

        //코틀린 코드구성
        //그룹정수id , item id ,순서
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드메뉴 1")
//        menu?.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "코드메뉴 2")
        val sub = menu?.addSubMenu("코드메뉴 2")
        sub?.add(Menu.NONE, Menu.FIRST+10, Menu.NONE, "코드메뉴 2_1")
        sub?.add(Menu.NONE, Menu.FIRST+20, Menu.NONE, "코드메뉴 2_2")

        menu?.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "코드메뉴 3")

        return true
    }

    //메뉴클릭시
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //XML
        /*when(item.itemId){
            R.id.item1 ->{}
            R.id.item2_1->{}
            R.id.item2_2->{}
            R.id.item3->{}
        }*/

        //코틀린코드 //Itemid가 들어간다
        when(item.itemId){
            Menu.FIRST ->{}
            Menu.FIRST+10->{}
            Menu.FIRST+20->{}
            Menu.FIRST+2->{}
        }

        return super.onOptionsItemSelected(item)
    }




}
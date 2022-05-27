package com.example.testingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.testingapp.databinding.ActivityMainBinding;
import com.example.testingapp.roomdb.User;
import com.example.testingapp.roomdb.UserDao;
import com.example.testingapp.roomdb.UserDatabase;
import com.example.testingapp.sqlite.SqlMainActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Datac datac;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 뷰 바인드 , MVVM 실습
         */
        setContentView(R.layout.activity_main);

        datac= new Datac();
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setActVar(datac);
        activityMainBinding.setLifecycleOwner(this);

        activityMainBinding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * Room DB 실습
         * 1.데이터 entity 모델 , Dao 인터페이스, RoomDB 추상클래스 생성
         * 2.RoomDB 추상클래스에 dao, entity 매핑
         * 3.db 빌더를 통해 db객체 빌드
         * 4.dao 인터페이스에 db객체 할당
         * 5.CRUD Operation
         */


        UserDatabase userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class , "User_DB")
                .fallbackToDestructiveMigration() //스키마 (Database)버전 변경 가능
                .allowMainThreadQueries()   //Main Thread에서 DB IO(입출력) 가능하게 함
                .build();



        mUserDao = userDatabase.userDao(); //인터페이스 객체 할당

        //데이터 삽입
        User user = new User("샘플1","22","2222-4444"); //
        mUserDao.setInsertUser(user);

        //데이터 조회
        List<User> userList = mUserDao.getUserAll();
        for(int i = 0 ; i<userList.size() ; i++){
            Log.d("TEST", userList.get(i).getName());
        }

        /**
         * SqLite DB Connect
         */


    }
}
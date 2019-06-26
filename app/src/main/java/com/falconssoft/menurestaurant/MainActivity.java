package com.falconssoft.menurestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.falconssoft.menurestaurant.Models.Users;

public class MainActivity extends AppCompatActivity {

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler=new DatabaseHandler(MainActivity.this);

        Users user=new Users("ahmad","1234");

        dbHandler.addUser(user);

        Initialize();


    }
    void Initialize (){



    }

}
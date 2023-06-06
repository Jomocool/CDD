package com.example.cdd.BeforeGaming.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cdd.BeforeGaming.UI.LoginInterfaceActivity;
import com.example.cdd.BeforeGaming.UI.RegisterInterfaceActivity;
import com.example.cdd.R;

public class StartInterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_interface);
    }

    //跳转到登录界面
    public void click_login(View view) {
        startActivity(new Intent(this, LoginInterfaceActivity.class));
    }

    //跳转到注册界面
    public void click_register(View view) {
        startActivity(new Intent(this, RegisterInterfaceActivity.class));
    }

    public void click_exit(View view) {
        finishAffinity();
    }
}
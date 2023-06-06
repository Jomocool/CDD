package com.example.cdd.BeforeGaming.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.BeforeGaming.Login.Login;
import com.example.cdd.Game.UI.GamingInterfaceActivity;
import com.example.cdd.R;

public class LoginInterfaceActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);
    }

    public void click_login(View view) {
        EditText edit_user_name=findViewById(R.id.edit_username);
        EditText edit_password=findViewById(R.id.edit_password);
        String user_name=edit_user_name.getText().toString();
        int password=Integer.parseInt(edit_password.getText().toString());
        Login login=new Login();
        boolean result=login.is_in(user_name,password);
        if(result==true)
        {
            //显示游戏界面
            startActivity(new Intent(this, GamingInterfaceActivity.class));
        }
        else
        {
            //弹出对话框
            System.out.println("弹出对话框");
        }

    }
}

package com.example.cdd.BeforeGaming.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.BeforeGaming.BeforeGaming;
import com.example.cdd.Game.UI.GamingInterfaceActivity;
import com.example.cdd.R;

public class LoginInterfaceActivity extends AppCompatActivity {


    public LoginInterfaceActivity() {

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_interface);
    }

    public void click_login2(View view) {
        EditText edit_user_name=findViewById(R.id.edit_username2);
        EditText edit_password=findViewById(R.id.edit_password2);

        String user_name=edit_user_name.getText().toString();
        String password=edit_password.getText().toString();

        boolean result= beforeGaming.is_in(user_name,password);

        if(result==true)//用户名和密码验证成功，显示游戏界面
            startActivity(new Intent(this, GamingInterfaceActivity.class));
        else//否则弹出对话框提示错误
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
    }

    private BeforeGaming beforeGaming =new BeforeGaming();
}

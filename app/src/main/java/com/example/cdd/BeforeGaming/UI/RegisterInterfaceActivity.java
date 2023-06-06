package com.example.cdd.BeforeGaming.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.BeforeGaming.BeforeGaming;
import com.example.cdd.R;

import java.io.IOException;

public class RegisterInterfaceActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_interface);
    }

    public void click_register2(View view) throws IOException {
        EditText et_name=findViewById(R.id.edit_username2);
        EditText et_password=findViewById(R.id.edit_password2);
        String name=et_name.getText().toString();
        String password=et_password.getText().toString();

        if(beforeGaming.get_map().containsKey(name)) {
            Toast.makeText(this, "用户名已存在,重新输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            beforeGaming.get_map().put(name,password);
            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        }


        //注册成功后，返回到登录界面
        startActivity(new Intent(this, LoginInterfaceActivity.class));
    }

    private BeforeGaming beforeGaming =new BeforeGaming();
}

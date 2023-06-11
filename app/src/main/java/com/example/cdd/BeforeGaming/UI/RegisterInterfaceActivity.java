package com.example.cdd.BeforeGaming.UI;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cdd.BeforeGaming.BeforeGaming;
import com.example.cdd.R;
import com.example.cdd.SQL.NameAndPasswordSQL;

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

        if(query_username_is_exist(name)) {
            Toast.makeText(this, "用户名已存在,重新输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        else
        {
            //插入数据到数据库
            insert(name,password);
            Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        }

        //注册成功后，返回到登录界面
        startActivity(new Intent(this, LoginInterfaceActivity.class));
    }

    //数据库的插入
    public void insert(String Name,String Password)
    {
        SQLiteOpenHelper helper= NameAndPasswordSQL.getInstance(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        if(db.isOpen())
        {
            ContentValues values=new ContentValues();
            values.put("name",Name);
            values.put("password",Password);

            db.insert("NameAndPassword",null,values);
        }
        db.close();
    }

    //查询用户名是否存在
    public boolean query_username_is_exist(String Name)
    {
        SQLiteOpenHelper helper= NameAndPasswordSQL.getInstance(this);
        SQLiteDatabase db=helper.getReadableDatabase();

        if(db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from NameAndPassword",null );

            while(cursor.moveToNext())
            {
                String NAME=cursor.getString(1);

                if(NAME.equals(Name))
                {
                    cursor.close();
                    db.close();
                    return true;
                }
            }
            cursor.close();
            db.close();
        }
        return false;
    }

    private BeforeGaming beforeGaming =new BeforeGaming();
}

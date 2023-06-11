package com.example.cdd.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NameAndPasswordSQL extends SQLiteOpenHelper {



    //由于采用单例模式，所以构造函数设为私有属性
    private NameAndPasswordSQL(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
    }

    //数据库初始化
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table NameAndPassword(_id integer primary key autoincrement, name text, password text)";
        db.execSQL(sql);
    }

    //数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //数据库,采用单例模式
    private static SQLiteOpenHelper dateBase;
    public static synchronized SQLiteOpenHelper getInstance(Context context)
    {
        //当单例没有实例化时，对其实例化
        if(dateBase==null)
            dateBase=new NameAndPasswordSQL(context,"NameAndPassword.db",null,1);
        return dateBase;
    }

    //
}

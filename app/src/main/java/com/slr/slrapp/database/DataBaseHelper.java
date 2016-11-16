package com.slr.slrapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/7 0007.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    //三个不同参数的构造函数
    //带全部参数的构造函数，此构造函数必不可少
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);

    }
    //带两个参数的构造函数，调用的其实是带三个参数的构造函数
    public DataBaseHelper(Context context,String name){
        this(context,name,VERSION);
    }
    //带三个参数的构造函数，调用的是带所有参数的构造函数
    public DataBaseHelper(Context context,String name,int version){
        this(context, name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table user(id int,name varchar(20))";

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

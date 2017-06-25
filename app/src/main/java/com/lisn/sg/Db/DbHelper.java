package com.lisn.sg.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;

/**
 * ****************************
 * 项目名称：SG
 * 创建人：lisn on 2017/6/25 08:18
 * 邮箱：cnlishan@163.com
 * 创建时间：2017/6/25.
 * 站在顶峰，看世界
 * 落在谷底，思人生
 * ****************************
 * 版权所有违法必究
 *
 * Sqlite数据库帮助类
 */
public class DbHelper extends SQLiteOpenHelper {
    /**
     *
     * @param context 上下文
     *  name    数据库名称
     *  version 版本号 从1开始 版本号只能增加，不能减少
     */
    public DbHelper(Context context) {
        super(context, "sg.db", null, 1);
    }

    /**
     * 应用程序初次运行时执行
     * 数据库第一次被创建时调用。适合用于创建表的结构    create table 表名(列名 数据类型（长度）,........);
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //在创建数据库的时候创建表的结构
        db.execSQL("create table  student(_id integer primary key autoincrement,sname varchar(10) not null, gender varchar(2) )");

    }

    /**
     * 数据库版本号更新发生改变时执行
     * 在数据库的版本被更新时调用。适合对表结构进行修改   alter table 表名  add 列名 数据类型(长度)
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table student add age integer");
    }
}

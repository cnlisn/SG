package com.lisn.sg.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2017/6/26.
 * 保存图片到Sqlite数据库
 */

public class SaveImgDbHelper extends SQLiteOpenHelper {
    public String TB_NAME = "tb_Img";
    private   String ID = "_id";
    public String IMAGE = "img";

    public SaveImgDbHelper(Context context) {
        super(context, "ImgDb.db", null, 1);
    }

    //在数据库创建时，图片字段的数据类型存储为 BLOB数据库插入操作
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TB_NAME + " ( " + ID + " integer primary key , " + IMAGE + " BLOB ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.lisn.sg.Fragment;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.lisn.sg.Db.DbHelper;
import com.lisn.sg.R;

/**
 * SQLite 数据库增删改查
 */
public class SQlcruqFragment extends Fragment implements View.OnClickListener {


    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_sqlcruq, container, false);
        Button add = (Button) view.findViewById(R.id.doAdd);
        Button Delete = (Button) view.findViewById(R.id.doDelete);
        Button Update = (Button) view.findViewById(R.id.doUpdate);
        Button Query = (Button) view.findViewById(R.id.doQuery);
        add.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        Query.setOnClickListener(this);
        return view;
    }



    /*
     * insert into 表名[(columnList)]  values(valuesList);
	 * update 表名 set 列名=新的列值[,列名=新的列值] where 子句
	 * delete from 表名 where 子句
	 * select 星/列名的列表   from 表名  [where 子句  group by子句  having子句  order by子句]
	 *
	 */

    //添加数据
    public void doAdd() {
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //使用db执行sql命令
        //db.execSQL("insert into student values(null,'张三丰','男')");
        db.execSQL("insert into student values(null,?,?)", new String[]{"张三丰", "男"});
        System.out.println("数据插入成功");
        db.close();//关闭数据库
        dbHelper.close();//关闭助手类
    }

    //修改数据
    public void doUpdate() {
        System.out.println("doUpdate");
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //使用db执行sql命令
        db.execSQL("update student set sname=?,gender=? where _id=?", new String[]{"苏有朋", "男", "1"});
        System.out.println("数据修改成功");
        db.close();//关闭数据库
        dbHelper.close();//关闭助手类

    }

    //删除数据
    public void doDelete() {
        System.out.println("doDelete");
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //使用db执行sql命令
        db.execSQL("delete from student where _id=?", new String[]{"1"});
        System.out.println("数据删除成功");
        db.close();//关闭数据库
        dbHelper.close();//关闭助手类
    }


    //查询数据
    public void doQuery() {
        System.out.println("doQuery");
        DbHelper dbHelper = new DbHelper(mContext);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //使用db执行sql命令
        Cursor cs = db.rawQuery("select sname,gender from student where _id=?", new String[]{"1"});
        //判断cs是否为null及是否有数据行
        if (cs != null && cs.getCount() != 0) {
            //循环提取游标数据
            while (cs.moveToNext()) {
                Toast.makeText(mContext, cs.getString(0) + "===" + cs.getString(1), Toast.LENGTH_SHORT).show();
                //System.out.println(cs.getString(0);  //获取sname列的值
                //System.out.println(cs.getString(1));  //获取gender列的值
            }
        }

        cs.close();//关闭游标
        db.close();//关闭数据库
        dbHelper.close();//关闭助手类


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.doAdd) {
            doAdd();
        } else if (view.getId() == R.id.doDelete) {
            doDelete();
        } else if (view.getId() == R.id.doUpdate) {
            doUpdate();
        } else if (view.getId() == R.id.doQuery) {
            doQuery();
        }
    }
}

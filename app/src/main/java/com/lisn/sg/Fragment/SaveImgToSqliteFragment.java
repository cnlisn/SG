package com.lisn.sg.Fragment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lisn.sg.Db.SaveImgDbHelper;
import com.lisn.sg.HomeActivity;
import com.lisn.sg.R;
import com.lisn.sg.Utils.ImageUtils;
import com.lisn.sg.Utils.LsUtils;

/**
 * 保存图片到Sqlite数据库
 */
public class SaveImgToSqliteFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "SaveImgToSqliteFragment";
    private ImageView iv_img;
    private ImageView iv_readImg;
    private ImageUtils imageUtils;
    private long position = -1;
    private HomeActivity mContext;

    public SaveImgToSqliteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = (HomeActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_save_img_to_sqlite, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mContext.SetToolbarTitle("保存图片到Sqlite数据库");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {// 不在最前端界面显示
        } else {// 重新显示到最前端中
            mContext.SetToolbarTitle("保存图片到Sqlite数据库");
        }
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        iv_img = (ImageView) view.findViewById(R.id.iv_img);
        iv_readImg = (ImageView) view.findViewById(R.id.iv_readImg);
        Button bt_saveImg = (Button) view.findViewById(R.id.bt_saveImg);
        Button bt_readImg = (Button) view.findViewById(R.id.bt_readImg);
        bt_saveImg.setOnClickListener(this);
        bt_readImg.setOnClickListener(this);
//        BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher)
        imageUtils = new ImageUtils(mContext);
        iv_img.setImageBitmap(imageUtils.ImageId2Bitmap(R.mipmap.ic_launcher));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_saveImg) {
            SaveImg();
        } else if (v.getId() == R.id.bt_readImg) {
            ReadImg();
        }
    }

    /**
     * 从数据库中读取Img
     */
    private void ReadImg() {
        Bitmap bmp = getBmp((int) position);
        if (bmp != null) {
            iv_readImg.setImageBitmap(bmp);
        } else {
            LsUtils.showToast("读取失败");
        }
    }

    public Bitmap getBmp(int position) {
        if (position == -1) {
            return null;
        }
        try {
            SaveImgDbHelper dbHelper = new SaveImgDbHelper(mContext);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select " + dbHelper.IMAGE + " from " + dbHelper.TB_NAME + " where _id=?", new String[]{String.valueOf(position)});
//            Cursor cursor = db.rawQuery("select "+dbHelper.IMAGE+" from "+dbHelper.TB_NAME,null);
//            if (cursor != null && cursor.getCount() != 0) {
//                //循环提取游标数据
//                while (cursor.moveToNext()) {
//                    System.out.println("==== "+cursor.getBlob(0).toString());  //获取dbHelper.IMAGE列的值
//                }
//            }
//            cursor.moveToPosition(position-1); //position从0开始所以-1

            cursor.moveToNext();
            byte[] in = cursor.getBlob(cursor.getColumnIndex(dbHelper.IMAGE));
            cursor.close();
            db.close();
            dbHelper.close();
            Bitmap bmpout = BitmapFactory.decodeByteArray(in, 0, in.length);
            return bmpout;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 保存图片到Img中
     */
    private void SaveImg() {
        byte[] img = imageUtils.ImageId2Byte(R.mipmap.ic_launcher);
        position = insert(img);
        Log.e(TAG, "SaveImg: " + position);
        if (position == -1) {
            LsUtils.showToast("图片插入失败");
        } else {
            LsUtils.showToast("图片插入成功 ID=" + position);
        }
    }

    //将图片以字节形式存储数据库读取操作
    public long insert(byte[] img) {
        SaveImgDbHelper dbHelper = new SaveImgDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.IMAGE, img);
        long result = db.insert(dbHelper.TB_NAME, null, cv);
        db.close();
        dbHelper.close();
        return result;
    }
}

package com.lisn.sg.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.lisn.sg.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2017/6/26.
 */

public class ImageUtils {
    private Context mContext;

    public ImageUtils(Context context) {
        this.mContext = context;
    }


    /**
     * 根据图片的资源ID获取图片的Byte数组
     * @param id 图片资源ID
     * @return
     */
    public byte[] ImageId2Byte(int id)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Bitmap bitmap = ((BitmapDrawable) mContext.getResources().getDrawable(id)).getBitmap();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    /**
     * 根据图片ID获取Bitmap对象
     * @param id 图片资源ID
     * @return
     */
    public Bitmap ImageId2Bitmap(int id){
        return BitmapFactory.decodeResource(mContext.getResources(), id);
    }
}

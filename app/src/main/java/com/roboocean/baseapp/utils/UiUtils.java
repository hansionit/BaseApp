package com.roboocean.baseapp.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Hasnion on 2016/7/25.
 */
public class UiUtils {

    /**
     * 仅适用于720*1280的标注
     * @param context
     * @param px
     * @return
     */
    public static int px2Dp2Px(Context context, float px) {
        return Dp2Px(context,px/2);
    }


    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    //从资源中获取Drawble
    public static BitmapDrawable getDrawableFromId(Context context, int id){
        Resources res = context.getResources();
        return (BitmapDrawable)res.getDrawable(id);
    }
    //从资源中获取Bitmap图像
    public static Bitmap getBitmapFromId(Context context, int id){
        Resources res = context.getResources();
        BitmapDrawable bitDraw = new BitmapDrawable(res.openRawResource(id));
        Bitmap bm = bitDraw.getBitmap();
        return bm;
        //mImageView.setImageBitmap(bm);
    }

    public static int getWindowWidth(Context context) {
        int width;
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        try {
            if (Build.VERSION.SDK_INT < 13) {
                DisplayMetrics displaymetrics = new DisplayMetrics();
                display.getMetrics(displaymetrics);
                width = displaymetrics.widthPixels;
            } else {
                Point size = new Point();
                display.getSize(size);
                width = size.x;
            }
        } catch (NoSuchMethodError e) {
            try {
                width = display.getWidth();
            } catch (Exception ex) {
                width = 200;
            }
        }
        return width;
    }

    public static int getWindowHeight(Context context) {
        int height;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        context.getApplicationContext().getResources().getDisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        try {
            if (Build.VERSION.SDK_INT < 13) {
                DisplayMetrics displaymetrics = new DisplayMetrics();
                display.getMetrics(displaymetrics);
                height = displaymetrics.heightPixels;
            } else {
                Point size = new Point();
                display.getSize(size);
                height = size.y;
            }
        } catch (NoSuchMethodError e) {
            try {
                height = display.getHeight();
            } catch (Exception ex) {
                height = 200;
            }
        }
        return height;
    }

}

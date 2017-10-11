package com.hansion.baseapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Description：图片显示工具类
 * Author: Hansion
 * Time: 2017/2/3 11:32
 */
public class ImageUtil {

    public static void with(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context).load(imageUrl).into(imageView);
    }
}

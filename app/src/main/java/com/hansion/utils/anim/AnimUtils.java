package com.hansion.utils.anim;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.roboocean.robosea.R;


/**
 * Description：
 * Author: Hansion
 * Time: 2017/5/3 11:25
 */
public class AnimUtils {
    public static Animation getTopInAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.top_in);
        return animation;
    }

    public static Animation getTopOutAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.top_out);
        return animation;
    }

    public static Animation getBottomInAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_in);
        return animation;
    }

    public static Animation getBottomOutAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_out);
        return animation;
    }

    public static Animation getLeftInAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.left_in);
        return animation;
    }

    public static Animation getLeftOutAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.left_out);
        return animation;
    }

    public static Animation getRightInAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.right_in);
        return animation;
    }

    public static Animation getRightOutAnim(Context context) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.right_out);
        return animation;
    }



    /**
     * 中心旋转动画
     *
     * @param fromDegrees 起始角度
     * @param toDegrees   结束角度
     * @param duration    动画时长
     */
    public static void rotateRecordListAnim(View view,float fromDegrees, float toDegrees, int duration) {
        RotateAnimation rotateAnimation = new RotateAnimation(
                fromDegrees, toDegrees,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(duration);//设置动画持续时间
        rotateAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.startAnimation(rotateAnimation);
    }

    /**
     * 水平缩放动画
     *
     * @param view      需要缩放的View
     * @param fromX     起始X坐标
     * @param toX       终止X坐标
     * @param duration  动画持续时间
     * @param fillAfter 动画执行完后是否停留在执行完的状态
     * @param isGone    动画结束后是否隐藏
     */
    public static void scaleH(View view, float fromX, float toX, int duration
            , boolean fillAfter, final boolean isGone, @Nullable final MyAnimationListener listener) {
        ScaleAnimation animation = new ScaleAnimation(fromX, toX, 1.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(duration);
        animation.setFillAfter(fillAfter);
        view.startAnimation(animation);
        if (listener == null) {
            return;
        }
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onMyAnimationEnd(isGone);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}

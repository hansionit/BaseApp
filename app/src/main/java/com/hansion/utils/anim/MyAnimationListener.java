package com.hansion.utils.anim;

import android.view.animation.Animation;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/9/14 9:44
 */
public abstract class MyAnimationListener implements Animation.AnimationListener {

    public abstract void onMyAnimationEnd(boolean isGone);

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
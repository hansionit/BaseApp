package com.hansion.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * 用于避免Handler引发的内存泄漏
 * @param <T>
 */
public abstract class WeakHandler<T> extends Handler {
    private WeakReference<T> mOwner;
    
    public WeakHandler(final T t) {
        this.mOwner = new WeakReference<T>(t);
    }
    
    public T getOwner() {
        return this.mOwner.get();
    }
}
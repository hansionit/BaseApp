package com.hansion.baseapp.presenter;


import com.hansion.baseapp.contract.BaseContract;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Description：对Activity进行弱引用处理的BasePresenter
 * Author: Hansion
 * Time: 2016/12/1 15:34
 */
public abstract class BasePresenter<V extends BaseContract.IBase> implements BaseContract.IBasePresenter{
    protected Reference<V> mViewRef;

    //建立关联
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    //获取view
    protected V getView() {
        return mViewRef.get();
    }

    //判断是否与View建立了关联
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    //该方法在activity或者Fragment的onDestory中调用
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}

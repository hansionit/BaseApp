package com.roboocean.baseapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roboocean.baseapp.contract.BaseContract;
import com.roboocean.baseapp.presenter.BasePresenter;
import com.roboocean.baseapp.utils.HLogUtil;
import com.roboocean.baseapp.utils.MyToast;

import butterknife.ButterKnife;

/**
 * Description：
 * Author: Hansion
 * Time: 2016/12/5 11:25
 */
public abstract class BaseFragment<V extends BaseContract.IBase, P extends BasePresenter<V>>  extends Fragment implements BaseContract.IBase {

    protected P mPresenter;

    //当前类名，打印使用
    private Class<? extends BaseFragment> mClassName = this.getClass();
    //贴附的activity
    protected Activity mActivity;
    protected View mRootView;

    //配合ViewPager懒加载相关
    protected boolean isVisible;
    private boolean isPrepared;
    private boolean isFirst = true;
    private ProgressDialog progressDialog;

    //----------------------------------   Fragment生命周期Log打印与管理   ---------------------------------------------------


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        HLogUtil.i(mClassName + " ----------> onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HLogUtil.i(mClassName + " ----------> onCreate");
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        ButterKnife.bind(this, mRootView);
        //初始化Presenter
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        initData(getArguments());
        initView();
        HLogUtil.i(mClassName + " ----------> onCreateView");
        return mRootView;
    }

    public View getRootView() {
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        HLogUtil.i(mClassName + " ----------> onActivityCreated");
        isPrepared = true;
        lazyLoad();
    }

    @Override
    public void onStart() {
        super.onStart();
        HLogUtil.i(mClassName + " ----------> onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        HLogUtil.i(mClassName + " ----------> onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        hideProgressDialog();
        HLogUtil.i(mClassName + " ----------> onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        HLogUtil.i(mClassName + " ----------> onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        HLogUtil.i(mClassName + " ----------> onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HLogUtil.i(mClassName + " ----------> onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        HLogUtil.i(mClassName + " ----------> onDetach");
    }


    //----------------------------------   Fragment的初始设置    ---------------------------------------------------


    /**
     * 初始化数据
     */
    protected void initData(Bundle arguments) {

    }

    /**
     * 初始化View
     */
    protected void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            return;
        }
        initData();
        isFirst = false;
    }

    public void initData() {  //懒加载，只会在第一次加载数据

    }

    protected void onInvisible() {  //懒加载，界面不可见时执行

    }

    /**
     * 设置根布局资源id
     */
    protected abstract int setLayoutResouceId();

    protected abstract P createPresenter();

    //获取依附的Activity
    public Activity getMyActivity() {
        return mActivity;
    }

    @Override
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(getActivity(), title, message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showToast(int resId) {
        MyToast.show(resId);
    }
}

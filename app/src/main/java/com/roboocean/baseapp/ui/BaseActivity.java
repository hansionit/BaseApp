package com.roboocean.baseapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.roboocean.baseapp.contract.BaseContract;
import com.roboocean.baseapp.presenter.BasePresenter;
import com.roboocean.baseapp.utils.AppStackManager;
import com.roboocean.baseapp.utils.HLogUtil;
import com.roboocean.baseapp.utils.MyToast;

import butterknife.ButterKnife;


/**
 * Description：MVP模式下的BaseActivity
 * Author: Hansion
 * Time: 2016/12/1 16:51
 */
public abstract class BaseActivity<V extends BaseContract.IBase, P extends BasePresenter<V>> extends FragmentActivity implements BaseContract.IBase {

    protected P mPresenter;
    //全屏标志
    public static final int SCREEN_FULL = 0;
    //当前类名，打印使用
    private Class<? extends BaseActivity> mClassName = this.getClass();
    private ProgressDialog progressDialog;


    //----------------------------------   Activity生命周期Log打印与管理   ---------------------------------------------------

    //声明为final,不允许子类对该方法进行覆写已防止子类未调用super.initView()方法对view控件进行绑定
    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置是否全屏，如果不全屏,设置状态栏颜色
        setScreenIsFull();
        //设置Layout
        setContentView(initContentView());
        //初始化Presenter
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        ButterKnife.bind(this);
        initView();
        initData();
        //添加到Activity栈管理器
        AppStackManager.getAppStackManager().addActivity(this);
        HLogUtil.i(mClassName + " ----------> oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        HLogUtil.i(mClassName + " ----------> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        HLogUtil.i(mClassName + " ----------> onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        HLogUtil.i(mClassName + " ----------> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        HLogUtil.i(mClassName + " ----------> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgressDialog();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        AppStackManager.getAppStackManager().removeOfStack(this);
        HLogUtil.i(mClassName + " ----------> onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            String FRAGMENTS_TAG = "Android:support:fragments";
            outState.remove(FRAGMENTS_TAG);
        }
    }


    //----------------------------------   Activity的初始设置   ---------------------------------------------------

    //设置是否是全屏，默认：notitlebar (FragmentActivity默认不带TitileBar)
    public int setFullScreen() {
        return -1;
    }

    protected abstract int initContentView();

    protected abstract void initView();

    protected abstract void initData();

    private void setScreenIsFull() {
        if (setFullScreen() == SCREEN_FULL) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    protected abstract P createPresenter();

    //----------------------------------   其他功能   ---------------------------------------------------

    //不携带数据跳转Activity
    public static void switchTo(Activity activity, Class<? extends Activity> targetActivity, boolean needFinish) {
        if (activity == null) {
            return;
        }
        activity.startActivity(new Intent(activity, targetActivity));
        if (needFinish) {
            activity.finish();
        }
    }

    @Override
    public void showToast(int resId) {
        MyToast.show(resId);
    }

    @Override
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(BaseActivity.this, title, message, true, false);
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

}

package com.hansion.baseapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hansion.baseapp.contract.BaseContract;
import com.hansion.baseapp.presenter.BasePresenter;
import com.hansion.utils.AppStackManager;
import com.hansion.utils.HLogUtil;
import com.hansion.utils.MyToast;
import com.hansion.utils.SharedPrefsUtil;
import com.hansion.view.slideback.SlideBackAppCompatActivity;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;


/**
 * Description：MVP模式下的BaseActivity
 * Author: Hansion
 * Time: 2016/12/1 16:51
 */
public abstract class BaseActivity<V extends BaseContract.IBase, P extends BasePresenter<V>>
        extends SlideBackAppCompatActivity implements BaseContract.IBase {

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
        //设置是否可以左滑返回，必须在super.onCreate（）之前
        setSlideable(isActivitySlideBack());
        super.onCreate(savedInstanceState);
        //设置是否全屏，如果不全屏,设置状态栏颜色
        setScreenIsFull();
        //设置语言
        changeAppLanguage();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        HLogUtil.i(mClassName + "----------> onConfigurationChanged");
        changeAppLanguage();
    }

    @Override
    protected void onStart() {
        super.onStart();
        HLogUtil.i(mClassName + " ----------> onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeAppLanguage();
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

    /**
     * 设置语言
     *
     * @param language "zh"  "en"   "auto"
     */
    public void changeLanguage(String language) {
        String value = SharedPrefsUtil.getValue(this, "language", "language", "auto");
        if (value.equals(language)) {
            return;
        }
        SharedPrefsUtil.putValue(this, "language", "language", language);
    }


    /**
     * 根据配置设置语言
     */
    public void changeAppLanguage() {
        String sta = SharedPrefsUtil.getValue(this, "language", "language", "auto");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        switch (sta) {
            case "en":
                conf.locale = Locale.ENGLISH;
                break;
            case "zh":
                conf.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            default:
                conf.locale = Locale.getDefault();
                break;
        }
        res.updateConfiguration(conf, dm);
    }


    //----------------------------------   Activity的初始设置   ---------------------------------------------------

    //是否需要侧滑返回功能
    public abstract boolean isActivitySlideBack();

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
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                //透明状态栏
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

                //解决MIUI8 白色状态栏 白色字体的问题
                //以下代码会添加一个白色状态栏和黑色文字,可自行根据情况使用并更改颜色
//                if (Build.MODEL.contains("MI")
//                        || Build.MODEL.contains("Mi")
//                        || Build.MODEL.contains("mi")) {
//                    SystemBarTintManager tintManager = new SystemBarTintManager(this);// 创建状态栏的管理实例
//                    tintManager.setStatusBarTintEnabled(true);// 激活状态栏设置
//                    tintManager.setNavigationBarTintEnabled(true);// 激活导航栏设置
//                    tintManager.setStatusBarTintColor(getResources().getColor(R.color.white));//设置状态栏颜色
//                    tintManager.setStatusBarDarkMode(true, this);//false 状态栏字体颜色是白色 true 颜色是黑色
//                }
            }
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


    //EditText自动弹出键盘
    public void autoShowSoftInput(final EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 300);
    }

    //手动隐藏软键盘
    public void autoHideSoftInput(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}

package com.hansion.baseapp.ui;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.hansion.baseapp.R;
import com.hansion.baseapp.contract.MainContract;
import com.hansion.baseapp.presenter.MainPresenter;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainActivity, MainPresenter> implements MainContract.IMain {

    @Override
    public boolean isActivitySlideBack() {
        return false;
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }


    @OnClick(R.id.mGoTablayoutActivity)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mGoTablayoutActivity:
                switchTo(this, TabLayoutActivity.class, false);
                break;
        }
    }


    //用于判定双击退出APP的时间
    private long lastBackPressedTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                long pressTime = System.currentTimeMillis();
                if (pressTime - lastBackPressedTime < 2000) {
                    mPresenter.exitApp();
                    return true;
                }
                Toast.makeText(this, R.string.press_again_quit, Toast.LENGTH_SHORT).show();
                lastBackPressedTime = pressTime;
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

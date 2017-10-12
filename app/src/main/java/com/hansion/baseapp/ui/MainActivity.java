package com.hansion.baseapp.ui;

import android.view.View;

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
}

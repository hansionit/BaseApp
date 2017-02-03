package com.roboocean.baseapp.ui;

import com.roboocean.baseapp.R;
import com.roboocean.baseapp.contract.MainContract;
import com.roboocean.baseapp.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainActivity, MainPresenter> implements MainContract.IMain {

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
}

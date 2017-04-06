package com.roboocean.baseapp.ui;

import com.roboocean.baseapp.R;
import com.roboocean.baseapp.contract.GuideContract;
import com.roboocean.baseapp.presenter.GuidePresenter;

public class GuideActivity extends BaseActivity<GuideActivity, GuidePresenter> implements GuideContract.IGuide {


    @Override
    protected int initContentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected GuidePresenter createPresenter() {
        return new GuidePresenter();
    }
}

package com.hansion.baseapp.ui;

import com.hansion.baseapp.R;
import com.hansion.baseapp.contract.GuideContract;
import com.hansion.baseapp.presenter.GuidePresenter;

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

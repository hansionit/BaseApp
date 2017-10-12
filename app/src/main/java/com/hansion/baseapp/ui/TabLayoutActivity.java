package com.hansion.baseapp.ui;


import android.support.design.widget.TabLayout;

import com.hansion.baseapp.R;
import com.hansion.baseapp.contract.TabLayoutContract;
import com.hansion.baseapp.presenter.TabLayoutPresenter;
import com.hansion.baseapp.presenter.adapter.TabFragmentPagerAdapter;
import com.hansion.view.NoScrollViewPager;

import butterknife.BindView;

public class TabLayoutActivity extends BaseActivity<TabLayoutActivity, TabLayoutPresenter>
        implements TabLayoutContract.ITabLayout {

    @BindView(R.id.mViewPager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    public boolean isActivitySlideBack() {
        return false;
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_tab_layout;
    }


    @Override
    protected void initView() {
        //设置ViewPager是否可以滑动
        mViewPager.setScanScroll(false);
        initTabLayoutAndPages();
    }

    private void initTabLayoutAndPages() {
        //配置ViewPager的Adapter
        TabFragmentPagerAdapter pagerAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(pagerAdapter);

        //将ViewPager与TabLayout关联起来
        tabLayout.setupWithViewPager(mViewPager);

        //为每个Tab设置自定义布局
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(pagerAdapter.getTabView(i));
            }
        }

        //将ViewPager的滑动与TabLayout的选择相互关联起来
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        //通过设置ViewPager当前选中Item来间接设置TabLayout的默认展示页
//        viewPager.setCurrentItem(1);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected TabLayoutPresenter createPresenter() {
        return new TabLayoutPresenter();
    }
}

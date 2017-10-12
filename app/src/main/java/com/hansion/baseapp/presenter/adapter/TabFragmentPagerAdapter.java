package com.hansion.baseapp.presenter.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hansion.baseapp.R;
import com.hansion.baseapp.ui.testfragment.FragmentA;
import com.hansion.baseapp.ui.testfragment.FragmentB;
import com.hansion.baseapp.ui.testfragment.FragmentC;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/10/12 14:35
 */
public class TabFragmentPagerAdapter  extends FragmentPagerAdapter {


    private String[] mTitles = new String[]{"聊天", "通讯录","我"};
    private int[] imageResId = new int[]{
            android.R.drawable.ic_menu_myplaces,
            android.R.drawable.ic_menu_slideshow,
            android.R.drawable.ic_menu_recent_history
    };
    private Context context;

    public TabFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new FragmentA();
                break;
            case 1:
                fragment = new FragmentB();
                break;
            case 2:
                fragment = new FragmentC();
                break;
            default:
                fragment = new FragmentA();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    //自定义Tab样式
    public View getTabView(int position) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
        TextView tv = (TextView) v.findViewById(R.id.news_title);
        tv.setText(mTitles[position]);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        img.setImageDrawable(context.getResources().getDrawable(imageResId[position]));
        return v;
    }
}
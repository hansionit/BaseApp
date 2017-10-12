package com.hansion.baseapp.ui.testfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hansion.baseapp.R;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/10/12 14:43
 */
public class TestBaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment,container, false);
        LinearLayout mLayout =  (LinearLayout) view.findViewById(R.id.mLayout);
        //随机生成颜色值
        int currColor = (int) -(Math.random() * (16777216 - 1) + 1);
        mLayout.setBackgroundColor(currColor);
        return view;
    }
}

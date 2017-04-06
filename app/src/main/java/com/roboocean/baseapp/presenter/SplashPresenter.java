package com.roboocean.baseapp.presenter;

import com.roboocean.baseapp.contract.SplashContract;
import com.roboocean.baseapp.ui.SplashActivity;
import com.roboocean.baseapp.utils.SharedPrefsUtil;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:22
 */
public class SplashPresenter extends BasePresenter<SplashActivity> implements SplashContract.ISplashPresenter {

    @Override
    public void isAppFirstRun() {
        boolean isFirstRun = SharedPrefsUtil.getValue(getView(), "isFirstRun","isFirstRun", true);
        if (isFirstRun) {
            SharedPrefsUtil.putValue(getView(),"isFirstRun", "isFirstRun", false);
            getView().goHome();
        } else {
            getView().goGuide();
        }
    }
}

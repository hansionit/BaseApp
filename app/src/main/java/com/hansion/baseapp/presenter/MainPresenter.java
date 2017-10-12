package com.hansion.baseapp.presenter;

import com.hansion.baseapp.contract.MainContract;
import com.hansion.baseapp.ui.MainActivity;
import com.hansion.utils.AppStackManager;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:22
 */
public class MainPresenter extends BasePresenter<MainActivity> implements MainContract.IMainPresenter {
    @Override
    public void exitApp() {
        //再此做退出APP需要执行的一些操作


        AppStackManager.getAppStackManager().AppExit();
    }
}

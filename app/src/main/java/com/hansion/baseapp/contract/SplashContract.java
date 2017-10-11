package com.hansion.baseapp.contract;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:24
 */
public interface SplashContract {

    interface  ISplashPresenter extends BaseContract.IBasePresenter{
        void isAppFirstRun();
    }

    interface  ISplash extends BaseContract.IBase {

        void goHome();

        void goGuide();
    }
}

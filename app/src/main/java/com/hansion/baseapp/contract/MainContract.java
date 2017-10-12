package com.hansion.baseapp.contract;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:24
 */
public interface MainContract {

    interface  IMainPresenter extends BaseContract.IBasePresenter{

        void exitApp();
    }

    interface  IMain extends BaseContract.IBase {
    }
}

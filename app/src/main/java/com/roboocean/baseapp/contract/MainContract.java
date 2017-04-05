package com.roboocean.baseapp.contract;

/**
 * Description：
 * Author: Hansion
 * Time: 2017/2/3 11:24
 */
public interface MainContract {

    interface  IMainPresenter extends BaseContract.IBasePresenter{

        void initTcpClient();

        void connTcpServer();
    }

    interface  IMain extends BaseContract.IBase {
    }
}

package com.roboocean.baseapp.contract;


/**
 * Description：Base --- Contract
 * Author: Hansion
 */
public interface BaseContract {
    interface  IBasePresenter {

    }

    interface  IBase {

        //显示吐司
        void showToast(int resId);

        //显示带ProgressBar的Dialog
        void showProgressDialog(String title, String message);

        //隐藏弹出的Dialog
        void hideProgressDialog();
    }
}

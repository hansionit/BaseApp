package com.roboocean.baseapp.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.roboocean.baseapp.App;


/**
 * Toast 工具类
 *
 * @author Hansion
 */
public class MyToast {

    //TODO 是否在Debug模式   上线前需要更改为false
    public static boolean LOG_DEBUG = true;
    private static Toast mCustomToast;
    private static Toast mToast;

    //短时间吐司
    public static void show(int resourceID) {
        show(resourceID, Toast.LENGTH_SHORT);
    }


    //TODO 上线前需将用此方法展示的Toast更改为 : 用strings.xml和上面的方法配合显示
    //短时间吐司
    public static void show(String text) {
        show(text, Toast.LENGTH_SHORT);
    }

    //自定义时长吐司
    public static void show(Integer resourceID, int duration) {
        String text = App.getAppContext().getResources().getString(resourceID);// 用于显示的文字
        show(text, duration);
    }

    //自定义时长吐司
    public static void show(@NonNull final String text, final int duration) {

        if (mToast == null) {
           if (App.getAppContext() == null) {
                HLogUtil.e("AppContext是null");
            }

            mToast = Toast.makeText(App.getAppContext(), text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }

        mToast.show();
    }

    public static void showDebugToast(String text) {
        try {
            if(LOG_DEBUG) {
                show(text);
            }
        } catch (Exception e) {
            HLogUtil.e(e.getMessage());
        }
    }


    /**
     * 在UI线程中显示Toast消息
     */
    public static void showOnUiThread(final Activity activity, final String message) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 自定义布局的Toast
     * @param tvString
     */
//    public static void showToast(String tvString) {
//        if (mCustomToast == null) {
//            mCustomToast = new Toast(App.getAppContext());
//        }
//        View layout = LayoutInflater.from(App.getAppContext()).inflate(R.layout.custiom_toast, null);
//        TextView text = (TextView) layout.findViewById(R.id.mToastText);
//        text.setText(tvString);
//        mCustomToast.setDuration(Toast.LENGTH_LONG);
//        mCustomToast.setView(layout);
//        mCustomToast.show();
//    }
}

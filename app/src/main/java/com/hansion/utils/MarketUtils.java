package com.hansion.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * Author: Hansion
 * Time: 2016/10/30 19:09
 * <p>
 * com.qihoo.appstore  360手机助手
 * com.taobao.appcenter    淘宝手机助手
 * com.tencent.android.qqdownloader    应用宝
 * com.hiapk.marketpho 安卓市场
 * cn.goapk.market 安智市场
 * com.meizu.mstore 魅族应用商店
 * com.coolapk.market 酷安
 */
public class MarketUtils {


    /**
     * 跳转至应用市场的应用详情页
     * @param context
     * @return 跳转结果 false代表手机没有安装应用市场或未知错误
     */
    public static boolean goToAppInfoActivity(Context context) {
        if(isInstalledMarket(context)) {
            try {
                Uri uri = Uri.parse("market://details?id="+ context.getPackageName());
                Intent intent1 = new Intent(Intent.ACTION_VIEW,uri);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            } catch (Exception e) {
                e.printStackTrace();
                return  false;
            }
            return  true;
        } else {
            return  false;
        }
    }



    /**
     * 判断手机是否安装了应用市场
     * @param context
     * @return
     */
    public static boolean isInstalledMarket(Context context) {
        ArrayList<String> allInstalledMarketList = getAllInstalledMarketList(context);
        if(allInstalledMarketList != null && allInstalledMarketList.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取已安装的应用商店的包名列表
     *
     * @param context
     * @return
     */
    public static ArrayList<String> getAllInstalledMarketList(Context context) {
        ArrayList<String> pakegesNames = new ArrayList<>();
        if (context == null) {
            return pakegesNames;
        }

        Intent intent = new Intent();
        intent.setData(Uri.parse("market://details?id=android.browser"));
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list != null) {
            for (ResolveInfo resolveInfo : list) {
                String packageName = "";
                try {
                    packageName = resolveInfo.activityInfo.packageName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(packageName)) {
                    pakegesNames.add(packageName);
                }
            }
        }
        return pakegesNames;
    }
}
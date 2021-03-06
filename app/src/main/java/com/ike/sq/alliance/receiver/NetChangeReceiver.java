package com.ike.sq.alliance.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ike.sq.alliance.utils.MyUtils;
import com.jude.utils.JUtils;

public class NetChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)|| action.equals(MyUtils.CONNECTIVITY_CHANGE)) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isAvailable()) {
                /////////////网络连接
                String name = netInfo.getTypeName();
                intent = new Intent("com.send.netchange");
                context.sendBroadcast(intent);
                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    /////WiFi网络
                    JUtils.Toast("WiFi网络");
                } else if (netInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                    /////有线网络
                    JUtils.Toast("有线网络");
                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    /////////数据流量
                    JUtils.Toast("数据流量");
                }
            } else {
                ////////网络断开
                JUtils.Toast("网络断开");
            }
        }
    }
}

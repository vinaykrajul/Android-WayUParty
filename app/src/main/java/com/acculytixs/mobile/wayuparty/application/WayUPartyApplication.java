package com.acculytixs.mobile.wayuparty.application;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.danikula.videocache.HttpProxyCacheServer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WayUPartyApplication extends Application {
    private HttpProxyCacheServer proxy;
    private static WayUPartyApplication currentApplication = null;


    public WayUPartyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        currentApplication = this;

    }


    public static HttpProxyCacheServer getProxy(Context context) {
        WayUPartyApplication app = (WayUPartyApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }


    public static WayUPartyApplication getInstance() {
        return currentApplication;
    }
    public static int dp2px(Context context, int dp) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);

        return (int) (dp * displaymetrics.density + 0.5f);
    }

    public static int dp2px(int dp) {
        WindowManager wm = (WindowManager) WayUPartyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        display.getMetrics(displaymetrics);

        return (int) (dp * displaymetrics.density + 0.5f);
    }

    public String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String strDate= formatter.format(now);

        return strDate;

    }

    public String getYesterdayDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String strDate= formatter.format(yesterday());

        return strDate;
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) WayUPartyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

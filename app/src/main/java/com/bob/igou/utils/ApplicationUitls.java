package com.bob.igou.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import com.bob.igou.app.BaseApplication;

/**
 * Created by Administrator on 2016/3/15.
 */
//全局工具
public class ApplicationUitls {
    //上下文
    public static Context getContext(){
        return BaseApplication.getmContext();
    }

    //资源文件
    public static Resources getResources(){
        return getContext().getResources();
    }

    public static String getString(int resId){
        return getResources().getString(resId);
    }
    //获得字符串资源数组
    public static String[] getStringArray(int resId){
        return getResources().getStringArray(resId);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static int getColor(int resId){
        return getResources().getColor(resId);
    }

    public static Handler getMainHandler(){
        return BaseApplication.getmMainHandler();
    }

    public static long getMainThreadId(){
        return BaseApplication.getmMainThreadId();
    }

    /**
     * 执行延时任务
     *
     */
    public static void postDelayed(Runnable task, int delayed)
    {
        getMainHandler().postDelayed(task, delayed);
    }

    /**
     * 移除任务
     *
     * @param task
     */
    public static void removeCallbacks(Runnable task)
    {
        getMainHandler().removeCallbacks(task);
    }

}

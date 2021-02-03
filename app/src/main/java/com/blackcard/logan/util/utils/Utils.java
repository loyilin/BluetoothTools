package com.blackcard.logan.util.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Administrator on 2017/2/14.
 */
public class Utils {
    //同步时间指令 3-9依次填入时间  例：[-86, 85, 0, 20, 18, 8, 6, 10, 16, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    public static byte[] ontime = {(byte) 0xaa, 0x55, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //发送告知在线数据获取成功指令
    public static byte[] oncard = {(byte) 0xaa, 0x55, 0x06, 0x00, 0x00, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //离线总数
    public static byte[] totalnumber = {(byte) 0xaa, 0x55, 0x01, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //获取某条时间的指令
    public static byte[] ofthe = {(byte) 0xaa, 0x55, 0x02, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //清楚数据指令
    public static byte[] remove = {(byte) 0xaa, 0x55, 0x03, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //获取电量以及查看是否正在充电
    public static byte[] battery = {(byte) 0xaa, 0x55, 0x04, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //查看是否可以升级
    public static byte[] clota = {(byte) 0xaa, 0x55, 0x05, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static byte[] ota = {(byte) 0xaa, 0x55, 0x05, 0x01, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //获取实时数据
    public static byte[] newtime = {(byte) 0xaa, 0x55, 0x06, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //查询是否绑定
    public static byte[] isbinding = {(byte) 0xaa, 0x55, 0x07, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //解除绑定
    public static byte[] removebinding = {(byte) 0xaa, 0x55, 0x07, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //绑定
    public static byte[] binding = {(byte) 0xaa, 0x55, 0x07, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //查询固件版本
//      public static byte[] version = {(byte) -86, 85, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6};

    public static byte GetCheckSum(byte[] data) {
        int cheksum = 0;
        byte checkbyte = 0;
        for (int i = 0; i < data.length - 1; i++) {
            cheksum += data[i];
        }
        checkbyte = (byte) (cheksum & 0xff);
        checkbyte = (byte) ((~checkbyte) + 1);
        return checkbyte;
    }

    /**
     * 判断程序是否在前台运行
     */
    public static boolean isRuning(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) return false;
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return false;
                } else return true;
            }
        }
        return true;
    }

    //判断是否是老卡  老卡17位是0  新卡是mac
    private static boolean isOldCard(byte[] bytes) {
        if (bytes == null || bytes.length < 16) return false;
        return bytes[16] == 0;
    }

    //判断新卡老卡获取mac
    public static String getMac(byte[] bytes) {
        if (bytes == null) return "";
        return String.format("%02x", bytes[3])
                + String.format("%02x", bytes[4])
                + String.format("%02x", bytes[5])
                + String.format("%02x", bytes[6])
                + (isOldCard(bytes) ? "" : String.format("%02x", bytes[7]) + String.format("%02x", bytes[16]));
    }

    //判断新卡老卡获取时间
    @SuppressLint("DefaultLocale")
    public static String getTime(byte[] bytes) {
        if (bytes == null) return "";
        return (isOldCard(bytes) ? bytes[7] : 20) + "" + bytes[8] + "-"
                + String.format("%02d", bytes[9]) + "-"
                + String.format("%02d", bytes[10]) + " "
                + String.format("%02d", bytes[11]) + ":"
                + String.format("%02d", bytes[12]) + ":"
                + String.format("%02d", bytes[13]);
    }

    /**
     * 是蓝牙基站设备
     * @param scanRecord    蓝牙广播
     * @return              是否是蓝牙基站设备
     */
    public static boolean isBaseStation(@NonNull byte[] scanRecord){
        return scanRecord.length > 16 && scanRecord[5] == 67 && scanRecord[6] == 68 && scanRecord[7] == 2;
    }

    /**
     * 是蓝牙胸卡设备
     * @param scanRecord    蓝牙广播
     * @return              是否是蓝牙胸卡设备
     */
    public static boolean isChestCard(@NonNull byte[] scanRecord){
        return scanRecord.length > 16 && scanRecord[5] == 67 && scanRecord[6] == 68 && scanRecord[7] == 85;
    }

    /**
     * 是蓝牙采集卡设备
     * @param scanRecord    蓝牙广播
     * @return              是否是蓝牙采集卡设备
     */
    public static boolean isAcquisitionCard(@NonNull byte[] scanRecord){
        return scanRecord.length > 16 && scanRecord[14] == 67 && scanRecord[15] == 68 && scanRecord[16] == 84;
    }
}

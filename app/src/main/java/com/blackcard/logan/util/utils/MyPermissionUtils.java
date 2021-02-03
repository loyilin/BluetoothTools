package com.blackcard.logan.util.utils;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.Utils;

import java.util.List;

/**
 * author: Logan
 * time  : 2018/9/3
 * desc  : 定位相关工具类
 */
public class MyPermissionUtils {


    public static void request(PermissionOnGranted onGranted, @PermissionConstants.Permission final String... permissions) {
        request(null, onGranted, permissions);
    }

    //普通权限请求
    public static void request(final Context context, final PermissionOnGranted onGranted, @PermissionConstants.Permission final String... permissions) {
        PermissionUtils.permission(permissions)
                .callback(new PermissionUtils.FullCallback() {
                    @Override
                    public void onGranted(List<String> permissionsGranted) {
                        if (onGranted != null) onGranted.onGranted(permissionsGranted);
                    }

                    @Override
                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                        if (context == null) return;
                        new MaterialDialog.Builder(context)
                                .title("提示")
                                .content("是否打开权限设置")
                                .positiveText("确定")
                                .negativeText("取消")
                                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        PermissionUtils.launchAppDetailsSettings();
                                    }
                                })
                                .show();
                    }
                }).request();
    }


    /**
     * 打开Gps设置界面
     */
    public static void openGpsSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 判断Gps是否可用
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isGpsEnabled() {
        LocationManager lm = (LocationManager) Utils.getApp().getSystemService(Context.LOCATION_SERVICE);
        return lm != null && lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 判断定位是否可用
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLocationEnabled() {
        LocationManager lm = (LocationManager) Utils.getApp().getSystemService(Context.LOCATION_SERVICE);
        return lm != null
                && (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                || lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        );
    }

    //请求成功回调
    public interface PermissionOnGranted {
        void onGranted(List<String> permissionsGranted);
    }
}

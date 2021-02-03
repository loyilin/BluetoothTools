package com.blackcard.logan.util.base;

import android.content.Context;

/**
 * Created by Logan on 2019/3/27.
 */
public interface View {
    void showToast(String info);

    void showToastLong(String info);

    void showProgress();

    void hideProgress();

    Context getcontext();

    void closeActivity();
}

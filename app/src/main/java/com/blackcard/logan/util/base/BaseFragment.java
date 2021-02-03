package com.blackcard.logan.util.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Created by Logan on 2019/3/27.
 */
public abstract class BaseFragment<M extends Model, V extends View, P extends BasePresenter> extends BaseMvpFragment<M, V, P> implements View {

    private MaterialDialog pdialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialized();
    }

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container);
    }

    @Override
    public void showToast(String info) {
        ToastUtils.showShort(info);
    }

    @Override
    public void showToastLong(String info) {
        ToastUtils.showLong(info);
    }

    @Override
    public void showProgress() {
        pdialog = new MaterialDialog.Builder(getcontext())
                .content("加载中...")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    @Override
    public void hideProgress() {
        if (pdialog != null && pdialog.isShowing()) {
            pdialog.dismiss();
            pdialog = null;
        }
    }

    @Override
    public Context getcontext() {
        return getContext();
    }

    @Override
    public void closeActivity() {
        if (getActivity() != null) getActivity().finish();
    }

    protected abstract @LayoutRes int getLayoutRes();

    protected abstract void initialized();
}

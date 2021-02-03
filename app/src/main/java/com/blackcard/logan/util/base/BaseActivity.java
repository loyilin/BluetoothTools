package com.blackcard.logan.util.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by Logan on 2019/3/27.
 */
public abstract class BaseActivity<M extends Model, V extends View, P extends BasePresenter> extends BaseMvpActivity<M, V, P> implements View {

    private MaterialDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialized();
    }

    /**
     * 应用字体库
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        pdialog = new MaterialDialog.Builder(this)
                .content("加载中...")
                .progress(true, 0)
                .cancelable(false)
                .show();
    }

    @Override
    public void hideProgress() {
        if (pdialog != null && pdialog.isShowing()) {
            pdialog.dismiss();
        }
        pdialog = null;
    }

    @Override
    public Context getcontext() {
        return this;
    }

    @Override
    public void closeActivity() {
        finish();
    }

    protected abstract @LayoutRes
    int getLayoutRes();

    protected abstract void initialized();
}

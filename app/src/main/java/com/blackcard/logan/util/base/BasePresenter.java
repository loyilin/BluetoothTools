package com.blackcard.logan.util.base;

import java.lang.ref.WeakReference;

/**
 * Created by Logan on 2019/3/27.
 */
public abstract class BasePresenter<M extends Model, V extends View> implements Presenter<M, V> {
    /**
     * 使用弱引用来防止内存泄漏
     */
    private WeakReference<V> wrf;
    private M model;

    @Override
    public void registerModel(M model) {
        this.model = model;
    }

    @Override
    public void registerView(V view) {
        wrf = new WeakReference<>(view);
    }

    @Override
    public V getView() {
        return wrf == null ? null : wrf.get();
    }

    public M getModel() {
        return model;
    }

    /**
     * 在Activity或Fragment销毁时调用View结束的方法
     */
    @Override
    public void destroy() {
        if (wrf != null) {
            wrf.clear();
            wrf = null;
        }
        onViewDestroy();
    }

    protected abstract void onViewDestroy();
}
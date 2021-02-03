package com.blackcard.logan.util.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * base带加载更多的adapter
 * Created by Administrator on 2017/11/17.
 */

public abstract class MyAdapter<T> extends BaseQuickAdapter<T, MyViewHolder> {
    public MyAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        init();
    }

    public MyAdapter(@Nullable List<T> data) {
        super(data);
        init();
    }

    public MyAdapter(int layoutResId) {
        super(layoutResId);
        init();
    }

    private void init() {
//        setLoadMoreView(new CustomLoadMoreView());
        setAnimation();
    }


    /**
     * 设置加载动画
     */
    private void setAnimation() {
        openLoadAnimation();
        openLoadAnimation(BaseQuickAdapter.SCALEIN);
        isFirstOnly(false);
    }

    @Override
    protected void convert(MyViewHolder helper, T item) {
        convertView(helper, item, helper.getAdapterPosition());
    }

    protected abstract void convertView(MyViewHolder helper, T item, int position);
}

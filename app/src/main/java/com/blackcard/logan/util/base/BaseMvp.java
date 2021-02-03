package com.blackcard.logan.util.base;

/**
 * Created by Logan on 2019/3/27.
 */
public interface BaseMvp<M extends Model, V extends View, P extends BasePresenter> {
    M createModel();

    V createView();

    P createPresenter();
}
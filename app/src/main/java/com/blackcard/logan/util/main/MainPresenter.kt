package com.blackcard.logan.util.main

import com.blackcard.logan.util.base.BasePresenter

/**
 * Created by Logan on 2019/3/27.
 */
class MainPresenter : BasePresenter<MainModel, MainView>() {
    fun getData() {//这里要注意判空（view和model可能为空）
        view?.setAdapter(model.functions)
    }

    override fun onViewDestroy() {//销毁Activity时的操作，可以停止当前的model

    }
}
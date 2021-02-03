package com.blackcard.logan.util.main

import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.base.Model
import com.blackcard.logan.util.beans.Function

/**
 * Created by Logan on 2019/3/27.
 */
interface MainModel : Model {
    /**
     * 从网络获取数据
     *
     * @return     方法模块集合
     */
    val functions: List<Function>

    val adapter: MyAdapter<Function>
}
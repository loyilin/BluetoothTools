package com.blackcard.logan.util.main

import com.blackcard.logan.util.base.View
import com.blackcard.logan.util.beans.Function

/**
 * Created by Logan on 2019/3/27.
 */
interface MainView : View {

    /**
     * 设置数据
     * @param functions 功能集合
     */
    fun setAdapter(functions: List<Function>)
}
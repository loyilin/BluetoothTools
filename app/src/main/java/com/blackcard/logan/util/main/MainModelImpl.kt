package com.blackcard.logan.util.main

import android.support.v7.widget.CardView
import android.view.ViewTreeObserver
import com.blackcard.logan.util.R
import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.adapter.MyViewHolder
import com.blackcard.logan.util.beans.Function
import com.blackcard.logan.util.bs.BsElectricActivity
import com.blankj.utilcode.util.StringUtils

/**
 * Created by Logan on 2019/3/27.
 */
class MainModelImpl : MainModel {

    override val functions: List<Function>
        get() {
            val functions = ArrayList<Function>()
            functions.add(Function(StringUtils.getString(R.string.activity_name_BsElectricActivity), R.drawable.ic_icon_bs, BsElectricActivity::class.java))
            return functions
        }


    override val adapter: MyAdapter<Function>
        get() = object : MyAdapter<Function>(R.layout.item_main) {
            public override fun convertView(helper: MyViewHolder, item: Function, position: Int) {
                helper.setText(R.id.tv_name, item.name)
                        .setImageResource(R.id.iv_icon, item.drawableRes)
                val cardview = helper.getView<CardView>(R.id.cardview)
                cardview.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        cardview.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        val params = cardview.layoutParams
                        params.height = cardview.measuredWidth
                    }
                })
            }
        }
}
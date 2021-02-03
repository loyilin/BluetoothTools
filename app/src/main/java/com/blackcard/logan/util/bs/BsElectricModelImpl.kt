package com.blackcard.logan.util.bs

import com.blackcard.logan.util.R
import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.adapter.MyViewHolder
import com.blackcard.logan.util.beans.BLEDevice
import com.blankj.utilcode.util.ColorUtils

/**
 * Created by Logan on 2019/3/28.
 */
class BsElectricModelImpl : BsElectricModel {

    override val adapter: MyAdapter<BLEDevice>
        get() = object : MyAdapter<BLEDevice>(R.layout.item_bs_electric) {
            override fun convertView(helper: MyViewHolder, item: BLEDevice, position: Int) {
                helper.setText(R.id.tv_mac, item.mac)
                        .setText(R.id.tv_rri, String.format("RSSI：%s", item.rri))
                        .setText(R.id.tv_electric, String.format("剩余电量：%s", item.electricRate))
                        .setBackgroundColor(R.id.ll_linearlayout, getColor(item.electric))
            }
        }

    private fun getColor(electric: Int): Int {
        if (electric < 20) return ColorUtils.getColor(R.color.clRed)
        return if (electric >= 60) ColorUtils.getColor(R.color.clGreen)
        else ColorUtils.getColor(R.color.clGreen_shallow)
    }
}

package com.blackcard.logan.util.bs

import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.base.Model
import com.blackcard.logan.util.beans.BLEDevice

/**
 * Created by Logan on 2019/3/28.
 */
interface BsElectricModel : Model {

    /**
     * 返回蓝牙设备适配器
     */
    val adapter: MyAdapter<BLEDevice>
}

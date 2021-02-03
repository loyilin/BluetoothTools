package com.blackcard.logan.util.bs

import com.blackcard.logan.util.base.View
import com.blackcard.logan.util.beans.BLEDevice

/**
 * Created by Logan on 2019/3/28.
 */
interface BsElectricView : View {

    /**
     * 扫描到基站回调
     * @param bleDevice     基站设备
     */
    fun onLeScan(bleDevice: BLEDevice)

    /**
     * 清除数据
     */
    fun cliearAdapterData()
}

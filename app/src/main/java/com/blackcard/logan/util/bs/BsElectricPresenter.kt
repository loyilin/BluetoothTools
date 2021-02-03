package com.blackcard.logan.util.bs

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import com.blackcard.logan.util.base.BasePresenter
import com.blackcard.logan.util.beans.BLEDevice
import com.blackcard.logan.util.utils.Electric
import com.blackcard.logan.util.utils.MyPermissionUtils
import com.blackcard.logan.util.utils.Utils
import com.blankj.utilcode.constant.PermissionConstants
import java.util.*

/**
 * Created by Logan on 2019/3/28.
 */
class BsElectricPresenter : BasePresenter<BsElectricModel, BsElectricView>() {
    private var map: MutableMap<String, BLEDevice> = HashMap()
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var le: BluetoothAdapter.LeScanCallback? = null

    /**
     * 扫描基站设备
     */
    fun scanBS() {
        val manager = view?.getcontext()?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        if (manager.adapter == null) {
            view?.closeActivity()
            view?.showToast("该设备不支持蓝牙")
            return
        }

        bluetoothAdapter = manager.adapter

        //请求系统授权定位权限
        MyPermissionUtils.request(view?.getcontext(), MyPermissionUtils.PermissionOnGranted {
            if (!MyPermissionUtils.isGpsEnabled()) {
                view?.showToastLong("请允许程序使用GPS定位权限,否则无法使用蓝牙功能")
                MyPermissionUtils.openGpsSettings()
            } else startScanBS()//开始扫描基站
        }, PermissionConstants.LOCATION)
    }

    /**
     * 权限已准备好，只要蓝牙开启就开始扫描
     *
     * 在BLE的广播帧中，MSD（Manufacturer Specific Data）制造商自定义数据可选项的类型值是0xFF,
     * 我们利用这个字段来定义需要的内容。前两个字节，填写本公司简称‘CD’，跟着SubType作为设备
     * 的识别ID（Device Id）, 再后边则是TLV格式的自定数据。具体格式定义如下
     * Byte 1: Length: 0x1a
     * Byte 2: Type: 0xff (Custom Manufacturer Packet)
     * Byte 3-4: Manufacturer ID : 0x4344 (CD)
     * Byte 5: Device Id(SubType): 0x02 (定位基站)
     * Byte 6: SubType Length: 0x15
     * Byte 7-22: Proximity UUID（末位6个字节传递的是BD ADDR）
     * Byte 23-24: Major (始终等于0x0001)
     * Byte 25-26: Minor(表示电量的ADC值)
     * Byte 27: Signal Power
     */
    private fun startScanBS() {
        if (bluetoothAdapter == null) return

        //没有开启蓝牙直接打开
        if (!bluetoothAdapter!!.isEnabled) {
            bluetoothAdapter?.enable()
            return
        }

        //清除当前数据
        map.clear()
        view?.cliearAdapterData()

        le = BluetoothAdapter.LeScanCallback { device, rssi, scanRecord ->
            if (scanRecord != null && Utils.isBaseStation(scanRecord) && !map.containsKey(device?.address)) {
                val bleDevice = BLEDevice(device.name, device.address, rssi, Electric.bety2Electric(scanRecord[23], scanRecord[24]))
                map[bleDevice.mac] = bleDevice
                view?.onLeScan(bleDevice)
            }
        }

        //开始扫描基站
        bluetoothAdapter!!.startLeScan(le)
    }


    /**
     * 点击菜单
     */
    fun restartScan() {
        view?.cliearAdapterData()
        map.clear()
    }

    /**
     * 停止扫描
     */
    private fun stopScan() {
        if (bluetoothAdapter != null && le != null) bluetoothAdapter!!.stopLeScan(le)
    }

    override fun onViewDestroy() {
        stopScan()
        map.clear()
    }
}



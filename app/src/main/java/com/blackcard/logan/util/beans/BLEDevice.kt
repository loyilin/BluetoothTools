package com.blackcard.logan.util.beans

/**
 * 自定义的BLE卡片
 *
 * @author Administrator
 */
class BLEDevice {
    var name: String
    var mac: String

    //信号大小
    var rri: Int = 0
    //电量  只有基站才有
    var electric: Int = 0

    val electricRate: String
        get() = "$electric%"

    constructor(mac: String) : this("", mac)

    constructor(name: String?, mac: String) : this(name, mac, 0)

    constructor(name: String?, mac: String, rri: Int) : this(name, mac, rri, 0)

    constructor(name: String?, mac: String, rri: Int, electric: Int) {
        this.name = name ?: ""
        this.mac = mac
        this.rri = rri
        this.electric = electric
    }
}

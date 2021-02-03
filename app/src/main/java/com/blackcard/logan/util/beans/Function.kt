package com.blackcard.logan.util.beans

import android.app.Activity

/**
 * Created by Logan on 2019/3/28.
 */
class Function(name: String, var drawableRes: Int, cls: Class<out Activity>) {
    var fid: Int = 0
    var name: String? = name
        get() = if (field == null) "" else field
    var cls: Class<out Activity>? = cls


}

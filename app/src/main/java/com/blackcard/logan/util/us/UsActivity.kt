package com.blackcard.logan.util.us

import com.blackcard.logan.util.BuildConfig
import com.blackcard.logan.util.R
import com.blackcard.logan.util.base.BaseActivity
import com.blackcard.logan.util.base.BasePresenter
import com.blackcard.logan.util.base.Model
import com.blackcard.logan.util.base.View
import kotlinx.android.synthetic.main.activity_us.*

/**
 * 关于
 */
class UsActivity : BaseActivity<Model, View, BasePresenter<Model,View>>() {

    override fun getLayoutRes(): Int = R.layout.activity_us

    override fun initialized() {
        tv_version.text = String.format(getString(R.string.us_version), BuildConfig.VERSION_NAME)
    }

    override fun createModel(): Model? = null

    override fun createView(): View? = null

    override fun createPresenter(): BasePresenter<Model, View>? = null
}

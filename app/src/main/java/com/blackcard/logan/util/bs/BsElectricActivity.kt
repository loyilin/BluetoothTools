package com.blackcard.logan.util.bs

import android.graphics.drawable.GradientDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.blackcard.logan.util.R
import com.blackcard.logan.util.adapter.DividerItemDecoration
import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.base.BaseActivity
import com.blackcard.logan.util.beans.BLEDevice
import com.blankj.utilcode.util.ColorUtils
import kotlinx.android.synthetic.main.activity_main.*

class BsElectricActivity : BaseActivity<BsElectricModel, BsElectricView, BsElectricPresenter>(), BsElectricView {
    lateinit var adapter: MyAdapter<BLEDevice>

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_bselectric_activity, menu)  //加载menu文件
        return true
    }

    /*override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menu.getItem(0).setTitle(if (presenter.isScaning) R.string.stop else R.string.reStart)
        return super.onPrepareOptionsMenu(menu)
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.menu_scan -> {
                presenter.restartScan()
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initialized() {
        //初始化recyclerview
        adapter = presenter.model.adapter
        adapter.closeLoadAnimation()
        recyclerview.layoutManager = LinearLayoutManager(this)
        val gd = GradientDrawable()
        gd.setColor(ColorUtils.getColor(R.color.clBackground))
        gd.setSize(recyclerview!!.width, 1)
        recyclerview.addItemDecoration(DividerItemDecoration(this).setDrawable(gd))
        recyclerview.adapter = adapter
        adapter.setEmptyView(R.layout.view_empty, recyclerview)

        presenter.scanBS()
    }

    /*override fun ScanComplete() {
        setTitle(R.string.activity_name_BsElectricActivity)
        invalidateOptionsMenu()
    }*/

    /*override fun startScanBS() {
        setTitle(R.string.Scanning_for_base_station)
        invalidateOptionsMenu()
    }*/

    override fun onLeScan(bleDevice: BLEDevice) {
        adapter.data.add(bleDevice)
        //对信号排序，优先显示信号强的
        adapter.data.sortWith(kotlin.Comparator { o1, o2 -> o2.rri.compareTo(o1.rri)})
        adapter.notifyDataSetChanged()
    }

    override fun cliearAdapterData() = adapter.setNewData(null)

    override fun createModel(): BsElectricModel = BsElectricModelImpl()

    override fun createView(): BsElectricView = this

    override fun createPresenter(): BsElectricPresenter = BsElectricPresenter()
}

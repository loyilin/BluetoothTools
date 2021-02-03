package com.blackcard.logan.util.main

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.blackcard.logan.util.R
import com.blackcard.logan.util.adapter.MyAdapter
import com.blackcard.logan.util.base.BaseActivity
import com.blackcard.logan.util.beans.Function
import com.blackcard.logan.util.us.UsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainModel, MainView, MainPresenter>(), MainView {

    lateinit var adapter: MyAdapter<Function>

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.menu_us -> startActivity(Intent(this, UsActivity::class.java))
        }
        return true
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun initialized() {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        recyclerview.layoutManager = GridLayoutManager(this, 3)
        adapter = presenter.model.adapter
        recyclerview.adapter = adapter
        adapter.setEmptyView(R.layout.view_empty, recyclerview)
        adapter.setOnItemClickListener { adapter1, view, position -> startActivity(Intent(this,this.adapter.getItem(position)?.cls)) }
        presenter.getData()
    }

    override fun createModel(): MainModel = MainModelImpl()

    override fun createView(): MainView = this

    override fun createPresenter(): MainPresenter = MainPresenter()


    override fun setAdapter(functions: List<Function>) = adapter.setNewData(functions)
}
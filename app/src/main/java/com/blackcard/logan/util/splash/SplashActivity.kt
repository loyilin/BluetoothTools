package com.blackcard.logan.util.splash

import android.animation.Animator
import android.content.Intent
import android.os.Build
import android.view.WindowManager
import com.blackcard.logan.util.R
import com.blackcard.logan.util.base.BaseActivity
import com.blackcard.logan.util.base.BasePresenter
import com.blackcard.logan.util.base.Model
import com.blackcard.logan.util.base.View
import com.blackcard.logan.util.main.MainActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import kotlinx.android.synthetic.main.item_main.*

/**
 * 欢迎页面
 */
class SplashActivity : BaseActivity<Model, View, BasePresenter<Model,View>>() {

    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun onBackPressed() {}

    override fun initialized() {
        setAppBar()
        YoYo.with(Techniques.FadeInLeft)
                .duration(3000)
                .withListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {}

                    override fun onAnimationEnd(animation: Animator) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                }).playOn(tv_name)
    }

    //设置适配
    private fun setAppBar() {
        if (supportActionBar != null) supportActionBar!!.hide()
        //       设置屏幕始终在前面，不然点击鼠标，重新出现虚拟按键
        window.decorView.systemUiVisibility = (android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav

                // bar
                or android.view.View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar

                or android.view.View.SYSTEM_UI_FLAG_IMMERSIVE)
        //适配刘海
        if (Build.VERSION.SDK_INT >= 28) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }

    override fun createModel(): Model? = null

    override fun createView(): View? = null

    override fun createPresenter(): BasePresenter<Model, View>? = null
}

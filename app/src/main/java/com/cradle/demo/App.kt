package com.cradle.demo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.cradle.viewtrace.TrackerWindowManager
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

class App : Application() {


    companion object {
        var track: TrackerWindowManager? = null

        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
                object : ClassicsHeader(context) {
                    override fun setTranslationY(translationY: Float) {
                        super.setTranslationY(translationY)
                        track?.setText("" + translationY)
                    }
                }//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }

        }
    }

    override fun onCreate() {
        super.onCreate()
        val sw1 = sw()
        registerActivityLifecycleCallbacks(sw1)
    }
}

class sw : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        App.track = activity?.let { TrackerWindowManager(it) }
        App.track?.addView()
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }
}

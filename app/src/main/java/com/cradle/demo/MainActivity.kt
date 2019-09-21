package com.cradle.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradle.viewtrace.TrackerWindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshLayout.setOnRefreshListener { refreshlayout ->
            refreshlayout.finishRefresh(2000/*,false*/)//传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener{ refreshlayout ->
            refreshlayout.finishLoadMore(2000/*,false*/)//传入false表示加载失败
        }
    }
}

package com.cradle.viewtrace

import android.content.Context
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.layout_floating.view.*

class FloatingView(private val mContext: Context) : LinearLayout(mContext) {
    private val mWindowManager: WindowManager
    private var mTvTrace: TextView? = null
    private var mTvClassName: TextView? = null
    private var mIvClose: ImageView? = null

    private lateinit var preP: Point
    private lateinit var curP: Point

    init {
        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        initView()
    }

    private fun initView() {
        View.inflate(mContext, R.layout.layout_floating, this)
        mTvTrace = findViewById<View>(R.id.tv_trace) as TextView
        mIvClose = findViewById<View>(R.id.iv_close) as ImageView

        mIvClose!!.setOnClickListener { Toast.makeText(mContext, "关闭悬浮框", Toast.LENGTH_SHORT).show() }

        mTvTrace?.text = ""
    }

    public fun setText(str :String){
        mTvTrace?.text=str
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> preP = Point(event.rawX.toInt(), event.rawY.toInt())

            MotionEvent.ACTION_MOVE -> {
                curP = Point(event.rawX.toInt(), event.rawY.toInt())
                val dx = curP.x - preP.x
                val dy = curP.y - preP.y

                val layoutParams = this.layoutParams as WindowManager.LayoutParams
                layoutParams.x += dx
                layoutParams.y += dy
                mWindowManager.updateViewLayout(this, layoutParams)
                mTvTrace?.text = "" + curP.x + "*" + curP.y
                preP = curP
            }
        }

        return false
    }

    companion object {
        val TAG = "FloatingView"
    }
}

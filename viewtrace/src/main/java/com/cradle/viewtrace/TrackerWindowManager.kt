package com.cradle.viewtrace

import android.content.Context
import android.graphics.PixelFormat
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.WindowManager

class TrackerWindowManager(private val mContext: Context) {
    private val mWindowManager: WindowManager

    private var mFloatingView: FloatingView? = null

    init {
        mWindowManager = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    fun addView() {
        if (mFloatingView == null) {
            mFloatingView = FloatingView(mContext)
            mFloatingView!!.layoutParams = LAYOUT_PARAMS

            mWindowManager.addView(mFloatingView, LAYOUT_PARAMS)
        }
    }

    fun removeView() {
        if (mFloatingView != null) {
            mWindowManager.removeView(mFloatingView)
            mFloatingView = null
        }
    }

    public fun setText(str:String){
        mFloatingView?.setText(str)
    }

    companion object {
        private val LAYOUT_PARAMS: WindowManager.LayoutParams

        init {
            val params = WindowManager.LayoutParams()
            params.x = 0
            params.y = 0
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.LEFT or Gravity.TOP

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
//            } else {
//                params.type = WindowManager.LayoutParams.TYPE_PHONE
//            }

            params.format = PixelFormat.RGBA_8888
            params.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE

            LAYOUT_PARAMS = params
        }
    }
}

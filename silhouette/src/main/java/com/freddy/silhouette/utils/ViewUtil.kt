package com.freddy.silhouette.utils

import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import com.freddy.silhouette.ext.dp

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 06:02
 * @email : freddychencsc@gmail.com
 */
object ViewUtil {

    fun expandViewTouchArea(v: View, size: Int = 10.0f.dp) {
        this.expandViewTouchArea(v, size, size, size, size)
    }

    fun expandViewTouchArea(v: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (v.parent == null) {
            return
        }

        (v.parent as View).post {
            val bounds = Rect()
            v.isEnabled = true
            v.getHitRect(bounds)
            bounds.top -= top
            bounds.bottom += bottom
            bounds.left -= left
            bounds.right += right
            val touchDelegate = TouchDelegate(bounds, v)
            if (View::class.java.isInstance(v.parent)) {
                (v.parent as View).touchDelegate = touchDelegate
            }
        }
    }
}
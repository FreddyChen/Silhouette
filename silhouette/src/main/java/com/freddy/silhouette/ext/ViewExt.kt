package com.freddy.silhouette.ext

import android.view.View
import com.freddy.silhouette.utils.ViewUtil

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 06:02
 * @email : freddychencsc@gmail.com
 */
fun View.expandViewTouchArea(size: Int = 10.0f.dp) {
    ViewUtil.expandViewTouchArea(this, size = size)
}

fun View.expandViewTouchArea(left: Int, top: Int, right: Int, bottom: Int) {
    ViewUtil.expandViewTouchArea(this, left = left, top = top, right = right, bottom = bottom)
}
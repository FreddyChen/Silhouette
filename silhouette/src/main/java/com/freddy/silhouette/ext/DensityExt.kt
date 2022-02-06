package com.freddy.silhouette.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.freddy.silhouette.utils.DensityUtil

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 05:57
 * @email : freddychencsc@gmail.com
 */
val Float.dp
    get() = DensityUtil.dp2px(this)

val Float.sp
    get() = DensityUtil.sp2px(this)

val Int.dp
    get() = DensityUtil.dp2px(toFloat())

val Int.sp
    get() = DensityUtil.sp2px(toFloat())

fun AppCompatActivity.getScreenWidth(): Int = DensityUtil.getScreenWidth(this.applicationContext)
fun AppCompatActivity.getScreenHeight(): Int = DensityUtil.getScreenWidth(this.applicationContext)

fun Fragment.getScreenWidth(): Int = DensityUtil.getScreenWidth(this.requireContext())
fun Fragment.getScreenHeight(): Int = DensityUtil.getScreenHeight(this.requireContext())
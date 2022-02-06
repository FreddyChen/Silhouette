package com.freddy.silhouette.config

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.annotation.IntDef
import com.freddy.silhouette.ext.dp

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 05:56
 * @email : freddychencsc@gmail.com
 */
const val TYPE_NONE = -1
const val TYPE_MASK = 0
const val TYPE_SELECTOR = 1
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    TYPE_NONE, TYPE_MASK, TYPE_SELECTOR
)
annotation class Type

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    GradientDrawable.RECTANGLE,
    GradientDrawable.OVAL,
    GradientDrawable.LINE,
    GradientDrawable.RING
)
annotation class Shape

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    GradientDrawable.LINEAR_GRADIENT,
    GradientDrawable.RADIAL_GRADIENT,
    GradientDrawable.SWEEP_GRADIENT
)
annotation class GradientType

const val INTERCEPT_TYPE_SUPER = 0
const val INTERCEPT_TYPE_TRUE = 1
const val INTERCEPT_TYPE_FALSE = 2
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.SOURCE)
@IntDef(
    INTERCEPT_TYPE_SUPER,
    INTERCEPT_TYPE_TRUE,
    INTERCEPT_TYPE_FALSE
)
annotation class InterceptType

const val GRADIENT_ORIENTATION_TOP_BOTTOM = 0
const val GRADIENT_ORIENTATION_TR_BL = 1
const val GRADIENT_ORIENTATION_RIGHT_LEFT = 2
const val GRADIENT_ORIENTATION_BR_TL = 3
const val GRADIENT_ORIENTATION_BOTTOM_TOP = 4
const val GRADIENT_ORIENTATION_BL_TR = 5
const val GRADIENT_ORIENTATION_LEFT_RIGHT = 6
const val GRADIENT_ORIENTATION_TL_BR = 7

const val ALPHA_NORMAL = 1.0f
const val DEFAULT_ALPHA_PRESSED = ALPHA_NORMAL * 0.7f
const val DEFAULT_ALPHA_DISABLED = ALPHA_NORMAL * 0.3f

val DEFAULT_MASK_BACKGROUND_COLOR: Int by lazy {
    Color.parseColor("#1a000000")
}
val DEFAULT_DISABLE_BACKGROUND_COLOR: Int by lazy {
    Color.parseColor("#cccccc")
}
val DEFAULT_CANCEL_OFFSET by lazy {
    8.dp
}
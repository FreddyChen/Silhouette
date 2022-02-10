package com.freddy.silhouette.widget.layout

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.freddy.silhouette.R
import com.freddy.silhouette.config.*

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 06:16
 * @email : freddychencsc@gmail.com
 */
class SleFrameLayout  : FrameLayout {

    @Type
    private var type: Int = TYPE_MASK

    @Shape
    private var shape: Int = GradientDrawable.RECTANGLE
    private var innerRadius: Int = 0
    private var innerRadiusRatio: Float = 0f
    private var thickness: Int = 0
    private var thicknessRatio: Float = 0f
    private var normalBackgroundColor: Int = 0
    private var pressedBackgroundColor: Int = 0
    private var disabledBackgroundColor: Int = 0
    private var selectedBackgroundColor: Int = 0
    private var strokeWidth: Int = 0
    private var dashWidth: Float = 0f
    private var dashGap: Float = 0f
    private var normalStrokeColor: Int = 0
    private var pressedStrokeColor: Int = 0
    private var disabledStrokeColor: Int = 0
    private var selectedStrokeColor: Int = 0
    private var cornersRadius: Float = 0f
    private var cornersTopLeftRadius: Float = 0f
    private var cornersTopRightRadius: Float = 0f
    private var cornersBottomLeftRadius: Float = 0f
    private var cornersBottomRightRadius: Float = 0f
    private var normalGradientColors: IntArray? = null
    private var pressedGradientColors: IntArray? = null
    private var disabledGradientColors: IntArray? = null
    private var selectedGradientColors: IntArray? = null
    private var gradientOrientation: Int = GRADIENT_ORIENTATION_TOP_BOTTOM

    @GradientType
    private var gradientType: Int = GradientDrawable.LINEAR_GRADIENT
    private var gradientCenterX: Float = 0f
    private var gradientCenterY: Float = 0f
    private var gradientRadius: Float = 0f

    private var maskBackgroundColor: Int = DEFAULT_MASK_BACKGROUND_COLOR
    private var cancelOffset: Int = DEFAULT_CANCEL_OFFSET

    @InterceptType
    private var interceptType: Int = INTERCEPT_TYPE_SUPER

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        context.obtainStyledAttributes(attrs, R.styleable.SleFrameLayout, defStyleAttr, 0)
            .apply {
                type = getInt(R.styleable.SleFrameLayout_sle_type, TYPE_MASK)
                shape =
                    getInt(R.styleable.SleFrameLayout_sle_shape, GradientDrawable.RECTANGLE)
                innerRadius =
                    getDimensionPixelSize(R.styleable.SleFrameLayout_sle_innerRadius, 0)
                innerRadiusRatio =
                    getFloat(R.styleable.SleFrameLayout_sle_innerRadiusRatio, 0f)
                thickness =
                    getDimensionPixelSize(R.styleable.SleFrameLayout_sle_thickness, 0)
                thicknessRatio = getFloat(R.styleable.SleFrameLayout_sle_thicknessRatio, 0f)
                normalBackgroundColor =
                    getColor(R.styleable.SleFrameLayout_sle_normalBackgroundColor, 0)
                pressedBackgroundColor =
                    getColor(R.styleable.SleFrameLayout_sle_pressedBackgroundColor, 0)
                disabledBackgroundColor =
                    getColor(
                        R.styleable.SleFrameLayout_sle_disabledBackgroundColor,
                        DEFAULT_DISABLE_BACKGROUND_COLOR
                    )
                selectedBackgroundColor =
                    getColor(R.styleable.SleFrameLayout_sle_selectedBackgroundColor, 0)
                strokeWidth =
                    getDimensionPixelSize(R.styleable.SleFrameLayout_sle_strokeWidth, 0)
                dashWidth = getDimension(R.styleable.SleFrameLayout_sle_dashWidth, 0f)
                dashGap = getDimension(R.styleable.SleFrameLayout_sle_dashGap, 0f)
                normalStrokeColor =
                    getColor(R.styleable.SleFrameLayout_sle_normalStrokeColor, 0)
                pressedStrokeColor =
                    getColor(
                        R.styleable.SleFrameLayout_sle_pressedStrokeColor,
                        normalStrokeColor
                    )
                disabledStrokeColor =
                    getColor(
                        R.styleable.SleFrameLayout_sle_disabledStrokeColor,
                        normalStrokeColor
                    )
                selectedStrokeColor =
                    getColor(
                        R.styleable.SleFrameLayout_sle_selectedStrokeColor,
                        normalStrokeColor
                    )
                cornersRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_cornersRadius, 0f)
                cornersTopLeftRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_cornersTopLeftRadius, 0f)
                cornersTopRightRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_cornersTopRightRadius, 0f)
                cornersBottomLeftRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_cornersBottomLeftRadius, 0f)
                cornersBottomRightRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_cornersBottomRightRadius, 0f)
                val normalGradientColorsResourceId =
                    getResourceId(R.styleable.SleFrameLayout_sle_normalGradientColors, 0)
                if (normalGradientColorsResourceId != 0) {
                    normalGradientColors = resources.getIntArray(normalGradientColorsResourceId)
                }
                val pressedGradientColorsResourceId =
                    getResourceId(R.styleable.SleFrameLayout_sle_pressedGradientColors, 0)
                if (pressedGradientColorsResourceId != 0) {
                    pressedGradientColors = resources.getIntArray(pressedGradientColorsResourceId)
                }
                val disabledGradientColorsResourceId =
                    getResourceId(R.styleable.SleFrameLayout_sle_disabledGradientColors, 0)
                if (disabledGradientColorsResourceId != 0) {
                    disabledGradientColors = resources.getIntArray(disabledGradientColorsResourceId)
                }
                val selectedGradientColorsResourceId =
                    getResourceId(R.styleable.SleFrameLayout_sle_selectedGradientColors, 0)
                if (selectedGradientColorsResourceId != 0) {
                    selectedGradientColors = resources.getIntArray(selectedGradientColorsResourceId)
                }
                gradientOrientation = getInt(
                    R.styleable.SleFrameLayout_sle_gradientOrientation,
                    GRADIENT_ORIENTATION_TOP_BOTTOM
                )
                gradientType =
                    getInt(
                        R.styleable.SleFrameLayout_sle_gradientType,
                        GradientDrawable.LINEAR_GRADIENT
                    )
                gradientCenterX =
                    getDimension(R.styleable.SleFrameLayout_sle_gradientCenterX, 0f)
                gradientCenterY =
                    getDimension(R.styleable.SleFrameLayout_sle_gradientCenterY, 0f)
                gradientRadius =
                    getDimension(R.styleable.SleFrameLayout_sle_gradientRadius, 0f)
                maskBackgroundColor = getColor(
                    R.styleable.SleFrameLayout_sle_maskBackgroundColor,
                    DEFAULT_MASK_BACKGROUND_COLOR
                )
                cancelOffset = getDimensionPixelSize(
                    R.styleable.SleFrameLayout_sle_cancelOffset,
                    DEFAULT_CANCEL_OFFSET
                )
                interceptType =
                    getInt(
                        R.styleable.SleFrameLayout_sle_interceptType,
                        INTERCEPT_TYPE_SUPER
                    )
                recycle()
            }
        init()
    }

    private fun init() {
        val normalDrawable =
            getDrawable(normalBackgroundColor, normalStrokeColor, normalGradientColors)
        var pressedDrawable: GradientDrawable? = null
        var disabledDrawable: GradientDrawable? = null
        val selectedDrawable: GradientDrawable?
        when (type) {
            TYPE_MASK -> {
                pressedDrawable = getDrawable(
                    normalBackgroundColor,
                    normalStrokeColor,
                    normalGradientColors
                ).apply {
                    colorFilter =
                        PorterDuffColorFilter(maskBackgroundColor, PorterDuff.Mode.SRC_ATOP)
                }
                disabledDrawable =
                    getDrawable(disabledBackgroundColor, disabledBackgroundColor)
            }
            TYPE_SELECTOR -> {
                pressedDrawable =
                    getDrawable(pressedBackgroundColor, pressedStrokeColor, pressedGradientColors)
                disabledDrawable = getDrawable(
                    disabledBackgroundColor,
                    disabledStrokeColor,
                    disabledGradientColors
                )
            }
        }
        selectedDrawable = getDrawable(
            selectedBackgroundColor,
            selectedStrokeColor,
            selectedGradientColors
        )
        background = StateListDrawable().apply {
            if(type != TYPE_NONE) {
                addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
            }
            addState(intArrayOf(-android.R.attr.state_enabled), disabledDrawable)
            addState(intArrayOf(android.R.attr.state_selected), selectedDrawable)
            addState(intArrayOf(), normalDrawable)
        }
    }

    private fun getDrawable(
        backgroundColor: Int,
        strokeColor: Int,
        gradientColors: IntArray? = null
    ): GradientDrawable {
        // 背景色相关
        val drawable = GradientDrawable()
        setupColor(drawable, backgroundColor)

        // 形状相关
        (drawable.mutate() as GradientDrawable).shape = shape
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.innerRadius = innerRadius
            if (innerRadiusRatio > 0f) {
                drawable.innerRadiusRatio = innerRadiusRatio
            }
            drawable.thickness = thickness
            if (thicknessRatio > 0f) {
                drawable.thicknessRatio = thicknessRatio
            }
        }

        // 描边相关
        if (strokeColor != 0) {
            (drawable.mutate() as GradientDrawable).setStroke(
                strokeWidth,
                strokeColor,
                dashWidth,
                dashGap
            )
        }

        // 圆角相关
        if (cornersRadius != 0.0f) {
            (drawable.mutate() as GradientDrawable).cornerRadius = cornersRadius
        } else {
            // 指定4个角点中每个角点的半径。对于每个角点，数组
            // 包含两个值，X半径，Y半径
            // 顺序为左上角、右上角、右下角、左下角
            (drawable.mutate() as GradientDrawable).cornerRadii = floatArrayOf(
                cornersTopLeftRadius,
                cornersTopLeftRadius,

                cornersTopRightRadius,
                cornersTopRightRadius,

                cornersBottomRightRadius,
                cornersBottomRightRadius,

                cornersBottomLeftRadius,
                cornersBottomLeftRadius,
            )
        }

        // 渐变相关
        (drawable.mutate() as GradientDrawable).gradientType = gradientType
        if (gradientCenterX != 0.0f || gradientCenterY != 0.0f) {
            (drawable.mutate() as GradientDrawable).setGradientCenter(
                gradientCenterX,
                gradientCenterY
            )
        }
        gradientColors?.let { colors ->
            (drawable.mutate() as GradientDrawable).colors = colors
        }
        var orientation: GradientDrawable.Orientation? = null
        when (gradientOrientation) {
            GRADIENT_ORIENTATION_TOP_BOTTOM -> {
                orientation = GradientDrawable.Orientation.TOP_BOTTOM
            }
            GRADIENT_ORIENTATION_TR_BL -> {
                orientation = GradientDrawable.Orientation.TR_BL
            }
            GRADIENT_ORIENTATION_RIGHT_LEFT -> {
                orientation = GradientDrawable.Orientation.RIGHT_LEFT
            }
            GRADIENT_ORIENTATION_BR_TL -> {
                orientation = GradientDrawable.Orientation.BR_TL
            }
            GRADIENT_ORIENTATION_BOTTOM_TOP -> {
                orientation = GradientDrawable.Orientation.BOTTOM_TOP
            }
            GRADIENT_ORIENTATION_BL_TR -> {
                orientation = GradientDrawable.Orientation.BL_TR
            }
            GRADIENT_ORIENTATION_LEFT_RIGHT -> {
                orientation = GradientDrawable.Orientation.LEFT_RIGHT
            }
            GRADIENT_ORIENTATION_TL_BR -> {
                orientation = GradientDrawable.Orientation.TL_BR
            }
        }
        orientation?.apply {
            (drawable.mutate() as GradientDrawable).orientation = this
        }
        return drawable
    }

    private fun setupColor(drawable: GradientDrawable, backgroundColor: Int) {
        if (backgroundColor != 0) {
            (drawable.mutate() as GradientDrawable).setColor(backgroundColor)
        }
    }

    fun setInterceptType(@InterceptType interceptType: Int) {
        this.interceptType = interceptType
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when (interceptType) {
            INTERCEPT_TYPE_TRUE -> {
                true
            }
            INTERCEPT_TYPE_FALSE -> {
                false
            }
            else -> {
                return super.onInterceptTouchEvent(ev)
            }
        }
    }
}
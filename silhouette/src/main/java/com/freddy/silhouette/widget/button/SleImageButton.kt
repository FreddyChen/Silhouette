package com.freddy.silhouette.widget.button

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.core.content.ContextCompat
import com.freddy.silhouette.R
import com.freddy.silhouette.config.*
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.RelativeCornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import kotlin.properties.Delegates

/**
 *
 * @author: FreddyChen
 * @date  : 2022/02/07 06:05
 * @email : freddychencsc@gmail.com
 */
class SleImageButton : ShapeableImageView, View.OnClickListener, View.OnTouchListener {

    companion object {
        const val TYPE_MASK = 0
        const val TYPE_ALPHA = 1
        const val TYPE_SELECTOR = 2
        const val TYPE_CHECKBOX = 3

        const val STYLE_NORMAL = 0
        const val STYLE_ROUNDED = 1
        const val STYLE_OVAL = 2
    }

    @Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER
    )
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        STYLE_NORMAL, STYLE_ROUNDED, STYLE_OVAL
    )
    annotation class Style

    @Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER
    )
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        TYPE_MASK, TYPE_ALPHA, TYPE_SELECTOR, TYPE_CHECKBOX
    )
    annotation class Type

    @Type
    private var type: Int = TYPE_MASK
    var normalResId: Int = 0
    set(value) {
        if (value != 0) {
            setImageResource(value)
        }
        field = value
    }

    @Style
    private var style: Int = STYLE_NORMAL
    private var pressedResId: Int by Delegates.notNull()
    private var disabledResId: Int by Delegates.notNull()
    private var checkedResId: Int by Delegates.notNull()
    private var uncheckedResId: Int by Delegates.notNull()
    var isChecked = false
    set(value) {
        field = value
        if (type != TYPE_CHECKBOX) return
        isSelected = isChecked
    }
    private var pressedAlpha: Float by Delegates.notNull()
    private var disabledAlpha: Float by Delegates.notNull()
    private var maskBackgroundColor: Int by Delegates.notNull()
    private var cancelOffset: Int by Delegates.notNull()
    private var cornersRadius: Float = 0f
    private var cornersTopLeftRadius: Float = 0f
    private var cornersTopRightRadius: Float = 0f
    private var cornersBottomLeftRadius: Float = 0f
    private var cornersBottomRightRadius: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context,
    attrs,
    defStyleAttr
    ) {
        context.obtainStyledAttributes(attrs, R.styleable.SleImageButton, defStyleAttr, 0).apply {
            type = getInt(R.styleable.SleImageButton_sle_ib_type, TYPE_MASK)
            style = getInt(R.styleable.SleImageButton_sle_ib_style, STYLE_NORMAL)
            normalResId = getResourceId(R.styleable.SleImageButton_sle_normalResId, 0)
            pressedResId = getResourceId(R.styleable.SleImageButton_sle_pressedResId, 0)
            disabledResId = getResourceId(R.styleable.SleImageButton_sle_disabledResId, 0)
            checkedResId = getResourceId(R.styleable.SleImageButton_sle_checkedResId, 0)
            uncheckedResId = getResourceId(R.styleable.SleImageButton_sle_uncheckedResId, 0)
            isChecked = getBoolean(R.styleable.SleImageButton_sle_isChecked, false)
            pressedAlpha =
                getFloat(R.styleable.SleImageButton_sle_pressedAlpha, DEFAULT_ALPHA_PRESSED)
            disabledAlpha =
                getFloat(R.styleable.SleImageButton_sle_disabledAlpha, DEFAULT_ALPHA_DISABLED)
            maskBackgroundColor = getColor(
                R.styleable.SleImageButton_sle_maskBackgroundColor,
                DEFAULT_MASK_BACKGROUND_COLOR
            )
            cancelOffset = getDimensionPixelSize(
                R.styleable.SleImageButton_sle_cancelOffset,
                DEFAULT_CANCEL_OFFSET
            )
            cornersRadius = getDimension(R.styleable.SleImageButton_sle_cornersRadius, 0f)
            cornersTopLeftRadius =
                getDimension(R.styleable.SleImageButton_sle_cornersTopLeftRadius, 0f)
            cornersTopRightRadius =
                getDimension(R.styleable.SleImageButton_sle_cornersTopRightRadius, 0f)
            cornersBottomLeftRadius =
                getDimension(R.styleable.SleImageButton_sle_cornersBottomLeftRadius, 0f)
            cornersBottomRightRadius =
                getDimension(R.styleable.SleImageButton_sle_cornersBottomRightRadius, 0f)
            recycle()
        }
        init()
    }

    private fun init() {
        initType()
        setStyle(style)
        setOnTouchListener(this)
        if (type == TYPE_CHECKBOX) {
            setOnClickListener(this)
        }
    }

    private fun initType() {
        when (type) {
            TYPE_MASK, TYPE_ALPHA -> {
                if (!isEnabled) {
                    alpha = disabledAlpha
                }
            }
            TYPE_SELECTOR -> {
                if (!isEnabled) {
                    setImageResource(disabledResId)
                }
            }
            TYPE_CHECKBOX -> {
                val sld = StateListDrawable()
                sld.addState(
                    intArrayOf(android.R.attr.state_selected),
                    ContextCompat.getDrawable(context, checkedResId)
                )
                sld.addState(
                    intArrayOf(-android.R.attr.state_selected),
                    ContextCompat.getDrawable(context, uncheckedResId)
                )
                background = sld
                isSelected = isChecked
            }
        }
    }

    fun setStyle(@Style style: Int) {
        this.style = style
        when (style) {
            STYLE_NORMAL -> {
                shapeAppearanceModel = ShapeAppearanceModel.Builder()
                    .setAllCorners(CornerFamily.ROUNDED, 0f)
                    .build()
            }
            STYLE_ROUNDED -> {
                shapeAppearanceModel = if (cornersRadius > 0f) {
                    ShapeAppearanceModel.Builder()
                        .setAllCorners(CornerFamily.ROUNDED, cornersRadius)
                        .build()
                } else {
                    ShapeAppearanceModel.Builder()
                        .setTopLeftCorner(CornerFamily.ROUNDED, cornersTopLeftRadius)
                        .setTopRightCorner(CornerFamily.ROUNDED, cornersTopRightRadius)
                        .setBottomLeftCorner(CornerFamily.ROUNDED, cornersBottomLeftRadius)
                        .setBottomRightCorner(CornerFamily.ROUNDED, cornersBottomRightRadius)
                        .build()
                }
            }
            STYLE_OVAL -> {
                shapeAppearanceModel = ShapeAppearanceModel.builder()
                    .setAllCornerSizes(RelativeCornerSize(0.5f))
                    .build()
            }
        }
    }

    override fun onClick(v: View?) {
        when (type) {
            TYPE_CHECKBOX -> {
                isChecked = !isChecked
                isSelected = isChecked
                onCheckedChangedListener?.invoke(isChecked)
            }
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (!isEnabled || !isClickable || type == TYPE_NONE) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                when (type) {
                    TYPE_MASK -> {
                        setColorFilter(maskBackgroundColor)
                    }
                    TYPE_ALPHA -> {
                        alpha = pressedAlpha
                    }
                    TYPE_SELECTOR -> {
                        if (pressedResId != 0) {
                            setImageResource(pressedResId)
                        }
                    }
                }
            }

            MotionEvent.ACTION_MOVE -> {
                val currentX = event.x
                val currentY = event.y
                if (currentX < (0 - cancelOffset) || currentX > (width + cancelOffset) || currentY < (0 - cancelOffset) || currentY > (height + cancelOffset)) {
                    when (type) {
                        TYPE_MASK -> {
                            clearColorFilter()
                        }
                        TYPE_ALPHA -> {
                            alpha = ALPHA_NORMAL
                        }
                        TYPE_SELECTOR -> {
                            if (normalResId != 0) {
                                setImageResource(normalResId)
                            }
                        }
                    }
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                when (type) {
                    TYPE_MASK -> {
                        clearColorFilter()
                    }
                    TYPE_ALPHA -> {
                        alpha = ALPHA_NORMAL
                    }
                    TYPE_SELECTOR -> {
                        if (normalResId != 0) {
                            setImageResource(normalResId)
                        }
                    }
                }
            }
        }
        return false
    }

    fun setType(@IntRange(from = TYPE_MASK.toLong(), to = TYPE_CHECKBOX.toLong()) type: Int) {
        this.type = type
        initType()
    }

    override fun setEnabled(enabled: Boolean) {
        when (type) {
            TYPE_ALPHA -> {
                alpha = if (enabled) ALPHA_NORMAL else disabledAlpha
            }
            TYPE_SELECTOR -> {
                if (enabled) {
                    if (normalResId != 0) setImageResource(normalResId)
                } else {
                    if (disabledResId != 0) setImageResource(disabledResId)
                }
            }
        }
        super.setEnabled(enabled)
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        // 页面跳转后再回来，需要重设按钮透明度
        if (hasWindowFocus) isEnabled = isEnabled
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isEnabled = isEnabled
    }

    private var onCheckedChangedListener: ((isChecked: Boolean) -> Unit)? = null
    fun setOnCheckedChangedListener(listener: (isChecked: Boolean) -> Unit) {
        this.onCheckedChangedListener = listener
    }
}
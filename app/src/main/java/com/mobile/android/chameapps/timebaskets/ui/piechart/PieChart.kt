package com.mobile.android.chameapps.timebaskets.ui.piechart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import java.util.*

class PieChart : FrameLayout {
    var total = 0f
    var palette = IntArray(5)

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!,
        attrs
    ) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context!!, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        palette[0] = Color.parseColor("#EEDECF3F") // yellow
        palette[1] = Color.parseColor("#EEd4d4ff") // cyan
        palette[2] = Color.parseColor("#EE4D4D4D") // gray
        palette[3] = Color.parseColor("#EE0090BD") // blue
        palette[4] = Color.parseColor("#EEF17CB0") // rose
    }

    var pieSlices: ArrayList<PieSlice>? = null
    fun setData(data: FloatArray?) {
        pieSlices = ArrayList()
        if (data == null) {
            invalidate()
            return
        }
        total = 0f
        for (i in data.indices) {
            total += data[i]
        }
        var startAngle = 0f
        var sweepAngle: Float
        for (i in data.indices) {
            sweepAngle = data[i] * (360f / total)
            val pieSlice = PieSlice(context, this)
            pieSlice.startAngele = startAngle
            pieSlice.sweepAngle = sweepAngle
            addView(pieSlice)
            pieSlices!!.add(pieSlice)
            pieSlice.paint.color = palette[i % 6]
            startAngle += sweepAngle
        }
    }

    fun setLabels(labels: Array<String?>) {
        for (i in pieSlices!!.indices) {
            pieSlices!![i].label = labels[i]
        }
    }

    var bounds: RectF? = null
    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()
        val rectSize = Math.min(width, height)
        val widthDiff = (width - rectSize) / 2
        val heightDiff = (height - rectSize) / 2
        bounds = RectF(widthDiff, heightDiff, rectSize + widthDiff, rectSize + heightDiff)
        if (pieSlices != null) for (pieSlice in pieSlices!!) {
            pieSlice.oval = bounds
        }
    }

    var preX = 0f
    var preY = 0f
    var angle = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            preX = event.x
            preY = event.y
        } else if (event.action == MotionEvent.ACTION_MOVE) {
            val diffX = preX - event.x
            val diffY = preY - event.y
            angle += diffX
        } else if (event.action == MotionEvent.ACTION_UP) {
            animate().interpolator = DecelerateInterpolator(1.5f)
            animate().rotation(angle)
            preX = event.x
            preY = event.y
        }
        return true
    }
}
package com.mobile.android.chameapps.timebaskets.ui.piechart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View


@SuppressLint("ViewConstructor")
class PieSlice(context: Context?, pieChart: PieChart) : View(context) {

    @JvmField
    var label: String? = null
    @JvmField
    var oval: RectF? = null
    @JvmField
    var startAngele = 0f
    @JvmField
    var sweepAngle = 0f
    @JvmField
    var paint: Paint
    var labelPaint: Paint
    var pieChart: PieChart
    var labelRect = Rect()

    override fun onDraw(canvas: Canvas) {
        canvas.drawArc(oval!!, startAngele, sweepAngle, true, paint)
        val angle = (startAngele + sweepAngle / 2) * D_TO_R
        canvas.save()
        if (label != null) {
            labelPaint.getTextBounds(label, 0, label!!.length, labelRect)
            var x =
                oval!!.centerX() + Math.cos(angle.toDouble()).toFloat() * (oval!!.width() / 5 + labelRect.width() / 3)
            val y =
                oval!!.centerY() + Math.sin(angle.toDouble()).toFloat() * (oval!!.height() / 5 + labelRect.width() / 3)
            x -= labelRect.width() / 2.toFloat()
            canvas.rotate(
                startAngele + sweepAngle / 2,
                x + labelRect.exactCenterX(),
                y + labelRect.exactCenterY()
            )
            canvas.drawText(label!!, x, y, labelPaint)
        }
        canvas.restore()
    }

    companion object {
        const val D_TO_R = 0.0174532925f
    }

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        this.pieChart = pieChart
        labelPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        labelPaint.color = Color.WHITE
        labelPaint.textSize = 40f

        val tf = Typeface.createFromAsset(context?.assets, "font/blogger_sans_medium.otf")
        paint.typeface = tf
    }
}
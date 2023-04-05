package com.example.themoviedatabase.presentation.widget.progress

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.themoviedatabase.R


class CircleProgress constructor(
    ctx: Context,
    attrs: AttributeSet
) : ConstraintLayout(ctx, attrs) {

    private var pb: ProgressBar? = null
    private var tvProgress: TextView? = null

    private var progress = 0

    init {
        LayoutInflater.from(ctx).inflate(R.layout.circle_progress_layout, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        pb = findViewById(R.id.pbCircleProgress)
        tvProgress = findViewById(R.id.tvCircleProgress)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        pb?.progress = progress
        tvProgress?.text = progress.toString()
//        val bounds: Rect = pb?.getProgressDrawable()?.getBounds()!!
//
//        if (progress > 70) {
//            pb?.setProgressDrawable(getAppDrawable(R.drawable.circular_progress_bar_bigger_70))
//        } else {
//            pb?.setProgressDrawable(getAppDrawable(R.drawable.circular_progress_bar_less_70))
//        }
//        pb?.getProgressDrawable()?.setBounds(bounds)
    }
}

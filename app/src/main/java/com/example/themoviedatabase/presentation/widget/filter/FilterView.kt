package com.example.themoviedatabase.presentation.widget.filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedatabase.R
import com.example.themoviedatabase.factory.RepoFactory

class FilterView constructor(
    ctx: Context,
    attrs: AttributeSet?
) : ConstraintLayout(ctx, attrs) {

    private val adapter by lazy {
        FilterAdapter()
    }

    private var rv: RecyclerView? = null
    private var layoutManager: LinearLayoutManager? = null

    private val display by lazy {
        RepoFactory.getFilterDisplayImpl()
    }

    var listener: IFilterViewCallback? = null

    init {
        LayoutInflater.from(ctx).inflate(R.layout.filter_view_layout, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        if (layoutManager == null) {
            layoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
        }

        rv = findViewById(R.id.rvOptionView)

        rv?.adapter = adapter
        rv?.layoutManager = layoutManager

        adapter.listner = object : IFilterListener {
            override fun onFilter(content: String) {
                listener?.onFilter(content)
            }
        }
        adapter.submitList(display.getListFilterDisplay())
    }

    interface IFilterViewCallback {
        fun onFilter(context: String)
    }
}

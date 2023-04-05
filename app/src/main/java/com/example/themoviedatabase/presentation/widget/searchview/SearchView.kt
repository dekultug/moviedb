package com.example.themoviedatabase.presentation.widget.searchview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.setOnSafeClick


class SearchView constructor(
    ctx: Context,
    attrs: AttributeSet?
) : ConstraintLayout(ctx, attrs) {

    private var edtSearch: EditText? = null
    private var tvSearch: TextView? = null
    private var onSearch: (() -> Unit)? = null

    init {
        LayoutInflater.from(ctx).inflate(R.layout.search_view_layout, this, true)
        initView(attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        edtSearch = findViewById(R.id.edtSearchView)
        tvSearch = findViewById(R.id.tvSearchView)

        tvSearch?.setOnSafeClick {
            onSearch?.invoke()
        }
    }

    private fun initView(attrs: AttributeSet?) {

    }

    fun search(search: (() -> Unit)?) {
        onSearch = search
    }
}

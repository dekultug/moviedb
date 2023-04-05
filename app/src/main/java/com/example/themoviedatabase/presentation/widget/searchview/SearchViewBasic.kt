package com.example.themoviedatabase.presentation.widget.searchview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.setOnSafeClick

class SearchViewBasic constructor(
    ctx: Context,
    attrs: AttributeSet?
) : ConstraintLayout(ctx, attrs) {

    private var edtSearch: EditText? = null
    private var ivSearch: ImageView? = null

    var listener: ISearchListener? = null

    init {
        LayoutInflater.from(ctx).inflate(R.layout.search_view_basic_layout,this,true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        edtSearch = findViewById(R.id.edtSearchViewBasic)
        ivSearch = findViewById(R.id.ivSearchViewBasic)

        edtSearch?.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null){
                    listener?.onTextChange(s)
                }
            }
        })

        edtSearch?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                listener?.onCloseSearch()
                edtSearch?.clearFocus()
            }
            true
        }

        ivSearch?.setOnSafeClick {
            edtSearch?.setText("")
            edtSearch?.clearFocus()
            listener?.onCloseSearch()
        }
    }
}

package com.example.themoviedatabase.presentation.account.list

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.databinding.ListItemBinding
import org.jsoup.Connection.Base

class ListAdapter: BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.list_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        TODO("Not yet implemented")
    }

    inner class ListVH(private val binding: ListItemBinding): BaseVH<>
}

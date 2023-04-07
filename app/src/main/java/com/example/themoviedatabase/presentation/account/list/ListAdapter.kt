package com.example.themoviedatabase.presentation.account.list

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseGridAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.getAppString
import com.example.themoviedatabase.databinding.ListItemBinding
import com.example.themoviedatabase.domain.model.list.alllist.CreatedListResponse

class ListAdapter : BaseAdapter() {

    override fun getLayoutResource(viewType: Int) = R.layout.list_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return ListVH(binding as ListItemBinding)
    }

    inner class ListVH(private val binding: ListItemBinding) : BaseVH<CreatedListResponse>(binding) {
        override fun onBind(data: CreatedListResponse) {
            super.onBind(data)
            binding.tvListTitle.text = data.name
            binding.tvListSize.text = getAppString(R.string.size_of_list, data.itemCount.toString())
            binding.tvListModifier.text = data.listType
        }
    }
}

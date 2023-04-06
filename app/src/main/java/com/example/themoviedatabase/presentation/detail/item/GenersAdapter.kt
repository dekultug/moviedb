package com.example.themoviedatabase.presentation.detail.item

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.databinding.GenersItemBinding
import com.example.themoviedatabase.domain.model.trending.detail.info.Genre

class GenersAdapter : BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.geners_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return GenersVH(binding as GenersItemBinding)
    }

    inner class GenersVH(private val binding: GenersItemBinding) : BaseVH<Genre>(binding) {
        override fun onBind(data: Genre) {
            super.onBind(data)
            if (absoluteAdapterPosition == dataList.size - 1) {
                binding.tvGenersAnd.gone()
            }

            binding.tvGenersName.text = data.name
        }
    }
}

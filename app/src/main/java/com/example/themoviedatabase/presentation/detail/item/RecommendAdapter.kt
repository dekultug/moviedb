package com.example.themoviedatabase.presentation.detail.item

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.databinding.RecommendItemBinding
import com.example.themoviedatabase.domain.model.trending.detail.recommend.ResultRecommend
import loadImage

class RecommendAdapter : BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.recommend_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return RecommendVH(binding as RecommendItemBinding)
    }

    inner class RecommendVH(private val binding: RecommendItemBinding) : BaseVH<ResultRecommend>(binding) {
        override fun onBind(data: ResultRecommend) {
            super.onBind(data)

            binding.ivRecommendImage.loadImage(data.getImageRecommend())
            binding.tvRecommendName.text = data.title

        }
    }
}

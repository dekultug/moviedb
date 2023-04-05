package com.example.themoviedatabase.presentation.home.trendingadpter

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.databinding.MoreItemBinding
import com.example.themoviedatabase.databinding.TrendingItemBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.home.IMoreListener
import loadImage

class MovieAdapter : BaseAdapter() {

    companion object {
        private const val VIEW_MORE_TYPE = 0
        private const val MOVIE_TRENDING = 1
    }

    var listener: IMoreListener? = null

    override fun getItemViewTypeCustom(position: Int): Int {
        return when (dataList[position]) {
            is TrendingResponse -> MOVIE_TRENDING
            else -> VIEW_MORE_TYPE
        }
    }

    override fun getLayoutResource(viewType: Int): Int {
        return when (viewType) {
            MOVIE_TRENDING -> R.layout.trending_item
            VIEW_MORE_TYPE -> R.layout.more_item
            else -> R.layout.trending_item
        }
    }

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return when (viewType) {
            MOVIE_TRENDING -> MovieTrendingVH(binding as TrendingItemBinding)
            VIEW_MORE_TYPE -> ViewMoreVH(binding as MoreItemBinding)
            else -> null
        }
    }

    inner class MovieTrendingVH(private val binding: TrendingItemBinding) : BaseVH<TrendingResponse>(binding) {

        init {
            binding.ivTrendingMore.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.showMoreAction(item)
                }
            }
        }

        override fun onBind(data: TrendingResponse) {
            super.onBind(data)
            binding.ivTrending.loadImage(data.getImageHome())
            if (data.voteAverage != null) {
                binding.cPTrendingUserScore.setProgress((data.voteAverage!! * 10).toInt())
            }
            binding.tvTrendingName.text = data.getNameMovie()
            binding.tvTrendingDateRelease.text = data.getActiveDate()
        }
    }

    inner class ViewMoreVH(private val binding: MoreItemBinding) : BaseVH<Unit>(binding) {

    }
}

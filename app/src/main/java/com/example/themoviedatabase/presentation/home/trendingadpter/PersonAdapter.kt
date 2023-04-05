package com.example.themoviedatabase.presentation.home.trendingadpter

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.databinding.LeaderBoardItemBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import loadImage

class PersonAdapter : BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.leader_board_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return PersonVH(binding as LeaderBoardItemBinding)
    }

    inner class PersonVH(private val binding: LeaderBoardItemBinding) : BaseVH<TrendingResponse>(binding) {
        override fun onBind(data: TrendingResponse) {
            super.onBind(data)
            binding.ivLeaderBoardAvatar.loadImage(data.getImageHome())
            binding.tvLeaderBoardName.text = data.getNameMovie()
            binding.tvLeaderBoardExp.text = data.popularity.toString()

            binding.pbLeaderBoardExp.max = AppConfig.maxPopularity
            binding.pbLeaderBoardExp.progress = data.getProgress()
        }
    }
}

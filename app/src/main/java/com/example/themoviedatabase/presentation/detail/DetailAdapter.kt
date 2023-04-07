package com.example.themoviedatabase.presentation.detail

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.getAppString
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.common.show
import com.example.themoviedatabase.databinding.*
import com.example.themoviedatabase.domain.model.trending.detail.actor.ActorResponse
import com.example.themoviedatabase.domain.model.trending.detail.info.BelongsToCollection
import com.example.themoviedatabase.domain.model.trending.detail.info.TrendingDetailResponse
import com.example.themoviedatabase.domain.model.trending.detail.recommend.RecommendResponse
import com.example.themoviedatabase.domain.model.trending.detail.review.UserReviewResponse
import com.example.themoviedatabase.presentation.detail.item.GenersAdapter
import com.example.themoviedatabase.presentation.detail.item.RecommendAdapter
import com.example.themoviedatabase.presentation.detail.item.ReviewAdapter
import com.example.themoviedatabase.presentation.detail.item.TopBilledCastAdapter
import loadImage

class DetailAdapter : BaseAdapter() {

    companion object {
        private const val INFO_TRENDING_TYPE = 0
        private const val TOP_BILLED_CAST_TRENDING_TYPE = 1
        private const val REVIEW_TRENDING_TYPE = 2
        private const val PART_OF_MOVIE_TRENDING_TYPE = 3
        private const val RECOMMEND_TRENDING_TYPE = 4
    }

    override fun getItemViewTypeCustom(position: Int): Int {
        Log.d("tunglv", "getItemViewTypeCustom: 1")
        return when (dataList[position]) {
            is TrendingDetailResponse -> INFO_TRENDING_TYPE
            is ActorResponse -> TOP_BILLED_CAST_TRENDING_TYPE
            is UserReviewResponse -> REVIEW_TRENDING_TYPE
            is BelongsToCollection -> PART_OF_MOVIE_TRENDING_TYPE
            is RecommendResponse -> RECOMMEND_TRENDING_TYPE
            else -> -1
        }
    }

    override fun getLayoutResource(viewType: Int): Int {
        Log.d("tunglv", "getItemViewTypeCustom: 2")
        return when (viewType) {
            INFO_TRENDING_TYPE -> R.layout.info_detail_trending_item
            TOP_BILLED_CAST_TRENDING_TYPE -> R.layout.top_billed_cast_detail_trending_item
            REVIEW_TRENDING_TYPE -> R.layout.user_review_detail_trending_item
            RECOMMEND_TRENDING_TYPE -> R.layout.recommend_detail_trending_item
            PART_OF_MOVIE_TRENDING_TYPE -> R.layout.other_part_movie_detail_trending_item
            else -> throw Exception("Invalid viewType")
        }
    }

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return when (viewType) {
            INFO_TRENDING_TYPE -> InfoVH(binding as InfoDetailTrendingItemBinding)
            TOP_BILLED_CAST_TRENDING_TYPE -> TopBilledCastVH(binding as TopBilledCastDetailTrendingItemBinding)
            REVIEW_TRENDING_TYPE -> ReviewVH(binding as UserReviewDetailTrendingItemBinding)
            RECOMMEND_TRENDING_TYPE -> RecommendVH(binding as RecommendDetailTrendingItemBinding)
            PART_OF_MOVIE_TRENDING_TYPE -> PartOfMovieVH(binding as OtherPartMovieDetailTrendingItemBinding)
            else -> null
        }
    }

    inner class InfoVH(private val binding: InfoDetailTrendingItemBinding) : BaseVH<TrendingDetailResponse>(binding) {
        private val adapter = GenersAdapter()

        init {
            binding.rvInfoTrendingGenres.adapter = adapter
            binding.rvInfoTrendingGenres.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        override fun onBind(data: TrendingDetailResponse) {
            super.onBind(data)
            adapter.submitList(data.genres)
            binding.ivInfoTrendingBackground.loadImage(data.getBackground())
            binding.ivInfoTrendingImage.loadImage(data.getImageTrending())
            binding.tvInfoTrendingName.text = data.getNameTrending()
            binding.tvInfoTrendingReleaseDate.text = getAppString(R.string.release_date, data.getActiveDate())
            binding.tvInfoTrendingContentOverview.text = data.overview
            if (data.voteAverage != null) {
                binding.cpbInfoTrending.setProgress((data.voteAverage!! * 10).toInt())
            }
        }
    }

    inner class TopBilledCastVH(private val binding: TopBilledCastDetailTrendingItemBinding) : BaseVH<ActorResponse>(binding) {
        private val adapter = TopBilledCastAdapter()

        init {
            binding.rvTopBillCastItem.adapter = adapter
            binding.rvTopBillCastItem.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        override fun onBind(data: ActorResponse) {
            super.onBind(data)
            adapter.submitList(data.cast)
        }
    }

    inner class ReviewVH(private val binding: UserReviewDetailTrendingItemBinding) : BaseVH<UserReviewResponse>(binding) {
        private val adapter = ReviewAdapter()
        private var isShow = false

        init {
            binding.rvUserReview.adapter = adapter
            binding.rvUserReview.layoutManager = object : LinearLayoutManager(binding.root.context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

            binding.ivUserReviewDetailShow.setOnSafeClick {
                isShow = !isShow
                if (isShow) {
                    binding.rvUserReview.show()
                    binding.ivUserReviewDetailShow.setImageResource(R.drawable.ic_up)
                } else {
                    binding.rvUserReview.gone()
                    binding.ivUserReviewDetailShow.setImageResource(R.drawable.ic_arrow_down)
                }
            }
        }

        override fun onBind(data: UserReviewResponse) {
            super.onBind(data)
            if (data.infoReviews?.isEmpty() == true) {
                binding.ivUserReviewDetailShow.gone()
            } else {
                binding.ivUserReviewDetailShow.show()
            }
            adapter.submitList(data.infoReviews)
        }
    }

    inner class PartOfMovieVH(private val binding: OtherPartMovieDetailTrendingItemBinding) : BaseVH<BelongsToCollection>(binding) {
        override fun onBind(data: BelongsToCollection) {
            super.onBind(data)
            binding.ivOtherPartMovieBackground.loadImage(data.getImage())
            binding.tvOtherPartMovieName.text = getAppString(R.string.part_of_the_movie, data.getNameFilm())
        }
    }

    inner class RecommendVH(private val binding: RecommendDetailTrendingItemBinding) : BaseVH<RecommendResponse>(binding) {

        private val adapter = RecommendAdapter()

        init {
            binding.rvRecomentDetailTrending.adapter = adapter
            binding.rvRecomentDetailTrending.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        override fun onBind(data: RecommendResponse) {
            super.onBind(data)
            adapter.submitList(data.resultRecommend)
        }
    }
}

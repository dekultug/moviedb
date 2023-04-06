package com.example.themoviedatabase.presentation.detail.item

import android.text.TextUtils
import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.*
import com.example.themoviedatabase.databinding.ReviewItemBinding
import com.example.themoviedatabase.domain.model.trending.detail.review.InfoReview
import loadImage

class ReviewAdapter : BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.review_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return ReviewVH(binding as ReviewItemBinding)
    }

    inner class ReviewVH(private val binding: ReviewItemBinding) : BaseVH<InfoReview>(binding) {

        private var isShowMore = false

        init {
            binding.tvReviewOption.setOnSafeClick {
                isShowMore = !isShowMore
                if (isShowMore){
                    binding.tvReviewOption.text = "Thu gọn"
                    binding.tvReviewContent.maxLines = Int.MAX_VALUE
                    binding.tvReviewContent.ellipsize = null
                }else{
                    binding.tvReviewContent.maxLines = 2
                    binding.tvReviewContent.ellipsize = TextUtils.TruncateAt.END
                    binding.tvReviewOption.text = "Xem thêm"
                }
            }
        }

        override fun onBind(data: InfoReview) {
            super.onBind(data)
            if (data.getLengthContent() >= 100){
                binding.tvReviewOption.show()
                binding.tvReviewOption.enable()
            }else{
                binding.tvReviewOption.hide()
                binding.tvReviewOption.disable()
            }
            binding.ivReviewAvartar.loadImage(data.getAvatarUser())
            binding.tvReviewName.text = data.getNameUser()
            binding.tvReviewDateCommented.text = data.getDateCommented()

            binding.tvReviewContent.text = data.content
        }
    }
}

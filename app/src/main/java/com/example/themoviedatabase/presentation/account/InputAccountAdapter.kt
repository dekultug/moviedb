package com.example.themoviedatabase.presentation.account

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.common.show
import com.example.themoviedatabase.databinding.InputAccountItemBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import loadImage

class InputAccountAdapter : BaseAdapter() {

    var listener: IListener? = null

    override fun getLayoutResource(viewType: Int) = R.layout.input_account_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return InputAccountVH(binding as InputAccountItemBinding)
    }

    inner class InputAccountVH(private val binding: InputAccountItemBinding) : BaseVH<TrendingResponse>(binding) {

        init {
            binding.ivInputAccountFavourite.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onDeleteFavourite(item)
                }
            }

            binding.ivInputAccountAddToList.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onAddToList(item)
                }
            }

            binding.ivInputAccountWatchList.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onDeleteWatchList(item)
                }
            }

            binding.ivInputAccountRate.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onPutRate(item)
                }
            }

            binding.ivInputAccountDelete.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onDeleteItem(item)
                }
            }

            binding.root.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? TrendingResponse
                if (item != null) {
                    listener?.onDetail(item)
                }
            }
        }

        override fun onBind(data: TrendingResponse) {
            super.onBind(data)
            binding.ivInputAccountImage.loadImage(data.getImageHome())
            binding.tvInputAccountName.text = data.getNameMovie()
            binding.tvInputAccountDate.text = data.getActiveDate()
            binding.tvInputAccountOverview.text = data.overview

            when (data.inputType) {
                TrendingResponse.INPUT_TYPE.FAVOURITE -> {
                    binding.ivInputAccountFavourite.show()
                    binding.ivInputAccountWatchList.gone()
                    binding.ivInputAccountRate.gone()
                }

                TrendingResponse.INPUT_TYPE.WATCHLIST -> {
                    binding.ivInputAccountFavourite.gone()
                    binding.ivInputAccountWatchList.show()
                    binding.ivInputAccountRate.gone()
                }

                TrendingResponse.INPUT_TYPE.RATE -> {
                    binding.ivInputAccountFavourite.gone()
                    binding.ivInputAccountWatchList.gone()
                    binding.ivInputAccountRate.show()
                }

                else -> {
                    binding.ivInputAccountFavourite.show()
                    binding.ivInputAccountWatchList.show()
                    binding.ivInputAccountRate.show()
                }
            }

            if (data.isFavourite == true) {
                binding.ivInputAccountFavourite.setImageResource(R.drawable.ic_pink_heart)
            } else {
                binding.ivInputAccountFavourite.setImageResource(R.drawable.ic_black_heart)
            }

            if (data.isWatchList == true) {
                binding.ivInputAccountWatchList.setImageResource(R.drawable.ic_orange_save)
            } else {
                binding.ivInputAccountWatchList.setImageResource(R.drawable.ic_black_save)
            }

            if (data.isRate == true) {
                binding.ivInputAccountRate.setImageResource(R.drawable.ic_yellow_rate)
            } else {
                binding.ivInputAccountRate.setImageResource(R.drawable.ic_black_rate)
            }
        }
    }

    interface IListener {
        fun onDeleteFavourite(item: TrendingResponse) {}
        fun onDeleteWatchList(item: TrendingResponse) {}
        fun onPutRate(item: TrendingResponse) {}
        fun onDetail(item: TrendingResponse){}

        fun onAddToList(item: TrendingResponse){}
        fun onDeleteItem(item: TrendingResponse) {}
    }
}

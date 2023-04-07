package com.example.themoviedatabase.presentation.home.rate

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.base.eventbus.EventBusManager
import com.example.themoviedatabase.common.*
import com.example.themoviedatabase.common.event.RateMovieSuccess
import com.example.themoviedatabase.databinding.RateFragmentBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState

class RateFragment : BaseBindingFragment<RateFragmentBinding>(R.layout.rate_fragment) {

    companion object {
        private const val MOVIE_KEY = "MOVIE_ID_KEY"
        private const val RATE_COUNT_KEY = "RATE_COUNT_KEY"

        fun getInstance(itemMovie: TrendingResponse?, rate: Int): RateFragment {
            val rateFragment = RateFragment()
            rateFragment.arguments = bundleOf(
                MOVIE_KEY to itemMovie,
                RATE_COUNT_KEY to rate
            )
            return rateFragment
        }
    }

    private val viewModel by viewModels<RateViewModel>()

    override fun onPrepareInitView() {
        super.onPrepareInitView()
        viewModel.itemMovie = arguments?.getParcelable(MOVIE_KEY)
        viewModel.valuesRate = (arguments?.getInt(RATE_COUNT_KEY) ?: INT_DEFAULT) / 2
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onInitView() {
        super.onInitView()
        binding.clRate.setOnSafeClick { }
        binding.flRateRoot.setOnSafeClick { backFragment() }
        binding.ivRateClose.setOnSafeClick { backFragment() }

        binding.tvRateTitle.text = viewModel.itemMovie?.getNameMovie()?.let { title ->
            getAppString(R.string.rate, title)
        }

        if (viewModel.valuesRate == INT_DEFAULT) {
            binding.ivRateRemoveStar.gone()
            binding.tvRateDone.apply {
                isEnabled = false
                setBackgroundColor(getAppColor(R.color.gray))
                setTextColor(getAppColor(R.color.white))
            }
        } else {
            binding.ivRateRemoveStar.show()
            binding.tvRateDone.apply {
                isEnabled = true
                background = getAppDrawable(R.drawable.shape_bg_white_stroke_blue_corner_4)
                setTextColor(getAppColor(R.color.black))
            }
        }

        binding.arbRateStar.let { view ->
            view.rating = (viewModel.valuesRate.toFloat())
            view.setOnTouchListener { _, event ->
                val rating = view.rating
                bottomState(rating)
                return@setOnTouchListener view.onTouchEvent(event)
            }
        }

        binding.tvRateDone.setOnSafeClick {
            when (viewModel.itemMovie?.mediaType) {
                getString(R.string._movie) -> viewModel.createRate(binding.arbRateStar.rating.toInt())
                getString(R.string._tv) -> viewModel.rateTv(binding.arbRateStar.rating.toInt())
            }
        }

        binding.ivRateRemoveStar.setOnSafeClick {
            binding.arbRateStar.rating = 0f
            when (viewModel.itemMovie?.mediaType) {
                getString(R.string._movie) -> viewModel.deleteRate()
                getString(R.string._tv) -> viewModel.deleteTvRate()
            }
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.createRateState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Bạn đã đánh giá movie với ${viewModel.valuesRate} sao", Toast.LENGTH_SHORT).show()
                        EventBusManager.instance?.postPending(RateMovieSuccess(getAppString(R.string._movie)))
                        backFragment()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.rateTvState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Bạn đã đánh giá tv với ${viewModel.valuesRate} sao", Toast.LENGTH_SHORT).show()
                        EventBusManager.instance?.postPending(RateMovieSuccess(getAppString(R.string._tv)))
                        backFragment()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.deleteRateState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Xóa rate thành công", Toast.LENGTH_SHORT).show()
                        EventBusManager.instance?.postPending(RateMovieSuccess(getAppString(R.string._movie)))
                        backFragment()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.deleteTvRateState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Xóa rate thành công", Toast.LENGTH_SHORT).show()
                        EventBusManager.instance?.postPending(RateMovieSuccess(getAppString(R.string._tv)))
                        backFragment()
                    }
                }
            })
        }
    }

    private fun bottomState(rating: Float) {
        if (rating < 1f) {
            binding.tvRateDone.apply {
                isEnabled = false
                setBackgroundColor(getAppColor(R.color.gray))
                setTextColor(getAppColor(R.color.white))
            }
        } else {
            binding.tvRateDone.apply {
                isEnabled = true
                background = getAppDrawable(R.drawable.shape_bg_white_stroke_blue_corner_4)
                setTextColor(getAppColor(R.color.black))
            }
        }
    }
}

package com.example.themoviedatabase.presentation.account.rate

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.common.FLOAT_DEFAULT
import com.example.themoviedatabase.databinding.RateAccountFragmentBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.account.AccountViewModel
import com.example.themoviedatabase.presentation.account.InputAccountAdapter
import com.example.themoviedatabase.presentation.addtolist.AddToListActivity
import com.example.themoviedatabase.presentation.detail.DetailActivity
import com.example.themoviedatabase.presentation.home.rate.RateFragment
import com.example.themoviedatabase.presentation.model.IViewListener
import com.example.themoviedatabase.presentation.widget.filter.FilterView
import coroutinesLaunch
import handleUiState

class RateFragment : BaseBindingFragment<RateAccountFragmentBinding>(R.layout.rate_account_fragment) {
    private val viewModel by activityViewModels<AccountViewModel>()

    private val adapter = InputAccountAdapter()

    override fun onInitBinding() {
        super.onInitBinding()
        binding.rvRate.adapter = adapter
        binding.rvRate.layoutManager = LinearLayoutManager(requireContext())

        binding.fvRate.listener = object : FilterView.IFilterViewCallback {
            override fun onFilter(content: String) {
                viewModel.mediaType = content
                when (content) {
                    getString(R.string._movie) -> viewModel.getRateList()
                    getString(R.string._tv) -> viewModel.getRateTvList()
                }
            }
        }

        adapter.listener = object : InputAccountAdapter.IListener {

            override fun onAddToList(item: TrendingResponse) {
                navigateTo(AddToListActivity::class.java, bundleOf(AddToListActivity.MOVIE_ID_KEY to item.id))
            }

            override fun onDeleteItem(item: TrendingResponse) {

            }

            override fun onDetail(item: TrendingResponse) {
                val newItem = item.copy()
                newItem.mediaType = viewModel.mediaType
                navigateTo(DetailActivity::class.java, bundleOf(DetailActivity.ITEM_TRENDING_KEY to newItem))
            }

            override fun onPutRate(item: TrendingResponse) {

                val newItem = item.copy()
                newItem.mediaType = viewModel.mediaType

                viewModel.rateValues = newItem.rating?: FLOAT_DEFAULT
                viewModel.itemMovie = newItem

                replaceFragment(
                    RateFragment.getInstance(
                        viewModel.itemMovie,
                        viewModel.rateValues.toInt()
                    )
                )

            }
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.rateListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    Log.d(TAG, "onSuccess: ${it.data?.size}")
                    if (it.data != null) {
                        adapter.submitList(it.data)
                    }
                }
            })
        }

    }
}

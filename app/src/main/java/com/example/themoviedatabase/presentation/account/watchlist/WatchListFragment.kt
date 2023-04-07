package com.example.themoviedatabase.presentation.account.watchlist

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.common.show
import com.example.themoviedatabase.databinding.WatchListAccountFragmentBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.account.AccountViewModel
import com.example.themoviedatabase.presentation.account.InputAccountAdapter
import com.example.themoviedatabase.presentation.addtolist.AddToListActivity
import com.example.themoviedatabase.presentation.detail.DetailActivity
import com.example.themoviedatabase.presentation.model.IViewListener
import com.example.themoviedatabase.presentation.widget.filter.FilterView
import coroutinesLaunch
import handleUiState

class WatchListFragment : BaseBindingFragment<WatchListAccountFragmentBinding>(R.layout.watch_list_account_fragment) {

    private val viewModel by activityViewModels<AccountViewModel>()

    private val adapter = InputAccountAdapter()

    override fun onInitBinding() {
        super.onInitBinding()
        binding.rvWatchList.adapter = adapter
        binding.rvWatchList.layoutManager = LinearLayoutManager(requireContext())
        binding.fvWatchList.listener = object : FilterView.IFilterViewCallback {
            override fun onFilter(content: String) {
                viewModel.mediaType = content
                when(content){
                    getString(R.string._movie) -> viewModel.getWatchList()
                    getString(R.string._tv) -> viewModel.getWatchListTv()
                }
            }
        }

        adapter.listener = object: InputAccountAdapter.IListener{
            override fun onDeleteWatchList(item: TrendingResponse) {
                viewModel.isWatchList = false
                val newItem = item.copy()
                newItem.mediaType = viewModel.mediaType
                viewModel.itemMovie = newItem
                viewModel.markWatchList()
            }

            override fun onDetail(item: TrendingResponse) {
                val newItem = item.copy()
                newItem.mediaType = viewModel.mediaType
                navigateTo(DetailActivity::class.java, bundleOf(DetailActivity.ITEM_TRENDING_KEY to newItem))
            }

            override fun onAddToList(item: TrendingResponse) {
                navigateTo(AddToListActivity::class.java, bundleOf(AddToListActivity.MOVIE_ID_KEY to item.id))
            }

            override fun onDeleteItem(item: TrendingResponse) {

            }
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.watchListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    Log.d(TAG, "onSuccess: ${it.data?.size}")
                    if (it.data != null) {
                        adapter.submitList(it.data)
                        if (it.data!!.isEmpty()){
                            binding.ivWatchListNoData.show()
                            binding.rvWatchList.gone()
                        }else{
                            binding.ivWatchListNoData.gone()
                            binding.rvWatchList.show()
                        }
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.markWatchListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    viewModel.updateWatchList()
                }
            })
        }
    }
}

package com.example.themoviedatabase.presentation.account.favoutite

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.databinding.FavouriteAccountFragmentBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.account.AccountViewModel
import com.example.themoviedatabase.presentation.account.InputAccountAdapter
import com.example.themoviedatabase.presentation.addtolist.AddToListActivity
import com.example.themoviedatabase.presentation.detail.DetailActivity
import com.example.themoviedatabase.presentation.model.IViewListener
import com.example.themoviedatabase.presentation.widget.filter.FilterView
import coroutinesLaunch
import handleUiState

class FavouriteFragment : BaseBindingFragment<FavouriteAccountFragmentBinding>(R.layout.favourite_account_fragment) {

    private val viewModel by activityViewModels<AccountViewModel>()

    private val adapter = InputAccountAdapter()

    override fun onInitBinding() {
        super.onInitBinding()
        binding.rvFavourite.adapter = adapter
        binding.rvFavourite.layoutManager = LinearLayoutManager(requireContext())

        binding.fvFavourite.listener = object : FilterView.IFilterViewCallback {
            override fun onFilter(content: String) {
                viewModel.mediaType = content
                when(content){
                    getString(R.string._movie) -> viewModel.getFavouriteMovieList()
                    getString(R.string._tv) -> viewModel.getFavouriteTvList()
                }
            }
        }

        adapter.listener = object: InputAccountAdapter.IListener{
            override fun onDeleteFavourite(item: TrendingResponse) {
                viewModel.isFavourite = false
                val newItem = item.copy()
                newItem.mediaType = viewModel.mediaType
                viewModel.itemMovie = newItem
                viewModel.markFavourite()
            }

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

        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.favouriteListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    Log.d(TAG, "onSuccess: ${it.data?.size}")
                    if (it.data != null) {
                        adapter.submitList(it.data)
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.markFavouriteState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    viewModel.updateFavouriteList()
                }
            })
        }
    }
}

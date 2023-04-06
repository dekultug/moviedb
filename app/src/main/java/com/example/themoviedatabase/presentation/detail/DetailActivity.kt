package com.example.themoviedatabase.presentation.detail

import ai.ftech.base.eventbus.IEvent
import android.util.Log
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.common.event.RateMovieSuccess
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.databinding.DetailActivityBinding
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.presentation.addtolist.AddToListActivity
import com.example.themoviedatabase.presentation.home.rate.RateFragment
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import org.greenrobot.eventbus.EventBus

class DetailActivity : BaseBindingActivity<DetailActivityBinding>(R.layout.detail_activity) {

    companion object {
        const val ITEM_TRENDING_KEY = "ITEM_TRENDING_KEY"
    }

    private val viewModel by viewModels<DetailViewModel>()

    private val adapter by lazy { DetailAdapter() }

    override fun getContainerId() = R.id.clDetailRoot

    override fun onPrepareInitView() {
        super.onPrepareInitView()

        viewModel.idTrending = intent?.getParcelableExtra(ITEM_TRENDING_KEY)
        viewModel.itemMovie = viewModel.idTrending

        viewModel.idTrending?.id?.let { id ->
            viewModel.getDetailTrending(id)
        }

        when (viewModel.itemMovie?.mediaType) {
            getString(R.string._movie) -> viewModel.getStateMovie()
            getString(R.string._tv) -> viewModel.getStateTv()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onEvent(event: IEvent) {
        super.onEvent(event)
        when (event) {
            is RateMovieSuccess -> {
                Log.d(TAG, "onEvent: ${event.type}")
                when (event.type) {
                    getString(R.string._movie) -> viewModel.getStateMovie()
                    getString(R.string._tv) -> viewModel.getStateTv()
                }
                EventBus.getDefault().removeStickyEvent(event);
            }
        }
    }

    override fun onInitView() {
        super.onInitView()
        binding.rvDetail.adapter = adapter
        binding.rvDetail.layoutManager = LinearLayoutManager(this)

        binding.ivDetailHeaderBack.setOnSafeClick {
            finish()
        }

        binding.ivDetailAddToList.setOnSafeClick {
            navigateTo(AddToListActivity::class.java, bundleOf(AddToListActivity.MOVIE_ID_KEY to viewModel.itemMovie?.id))
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.detailTrendingState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    adapter.submitList(it.data)
                }
            })
        }

        coroutinesLaunch(viewModel.movieState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        updateUI(it.data!!)
                        canInputUser()
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.markFavouriteState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data == true) {
                        binding.ivDetailFavourite.setImageResource(R.drawable.ic_pink_heart)
                    } else {
                        binding.ivDetailFavourite.setImageResource(R.drawable.ic_heart_white)
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.markWatchListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data == true) {
                        binding.ivDetailWatchList.setImageResource(R.drawable.ic_orange_save)
                    } else {
                        binding.ivDetailWatchList.setImageResource(R.drawable.ic_save_white)
                    }
                }
            })
        }

    }

    private fun updateUI(stateMovieResponse: StateMovie) {
        if (stateMovieResponse.favorite == true) {
            binding.ivDetailFavourite.setImageResource(R.drawable.ic_pink_heart)
        } else {
            binding.ivDetailFavourite.setImageResource(R.drawable.ic_heart_white)
        }

        if (stateMovieResponse.watchlist == true) {
            binding.ivDetailWatchList.setImageResource(R.drawable.ic_orange_save)
        } else {
            binding.ivDetailWatchList.setImageResource(R.drawable.ic_save_white)
        }
        Log.d(TAG, "updateUI: ${viewModel.rateValues} ")
        if (viewModel.rateValues > 0f) {
            binding.ivDetailRate.setImageResource(R.drawable.ic_yellow_rate)
        } else {
            binding.ivDetailRate.setImageResource(R.drawable.ic_white_rate)
        }
    }

    private fun canInputUser() {
        binding.ivDetailFavourite.setOnSafeClick {
            viewModel.isFavourite = !viewModel.isFavourite
            viewModel.markFavourite()
        }

        binding.ivDetailWatchList.setOnSafeClick {
            viewModel.isWatchList = !viewModel.isWatchList
            viewModel.markWatchList()
        }

        binding.ivDetailRate.setOnSafeClick {
            replaceFragment(
                RateFragment.getInstance(
                    viewModel.itemMovie,
                    viewModel.rateValues.toInt()
                )
            )
        }
    }
}

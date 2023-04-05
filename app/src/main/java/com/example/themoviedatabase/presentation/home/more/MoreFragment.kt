package com.example.themoviedatabase.presentation.home.more

import ai.ftech.base.eventbus.IEvent
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.common.event.RateMovieSuccess
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.databinding.MoreFragmentBinding
import com.example.themoviedatabase.domain.model.moviestate.state.rate.StateMovie
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.addtolist.AddToListActivity
import com.example.themoviedatabase.presentation.home.rate.RateFragment
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import org.greenrobot.eventbus.EventBus

class MoreFragment : BaseBindingFragment<MoreFragmentBinding>(R.layout.more_fragment) {
    companion object {
        private const val MOVIE_MORE_KEY = "MOVIE_MORE_KEY"

        fun getInstance(item: TrendingResponse): MoreFragment {
            val moreFragment = MoreFragment()
            moreFragment.arguments = bundleOf(MOVIE_MORE_KEY to item)
            return moreFragment
        }
    }

    private val viewModel by viewModels<MoreViewModel>()

    override fun getContainerId() = R.id.flMore

    override fun onPrepareInitView() {
        super.onPrepareInitView()
        viewModel.itemMovie = arguments?.getParcelable(MOVIE_MORE_KEY)
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
        binding.flMore.setOnSafeClick {
            backFragment()
        }
        binding.clMore.setOnSafeClick { }

        binding.llMoreAddToList.setOnSafeClick {
            navigateTo(AddToListActivity::class.java, bundleOf(AddToListActivity.MOVIE_ID_KEY to viewModel.itemMovie?.id))
        }

        binding.tvMoreTitle.text = viewModel.itemMovie?.getNameMovie()
        binding.ivMoreClose.setOnSafeClick { backFragment() }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()

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

        coroutinesLaunch(viewModel.tvState) {
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
                        binding.ivMoreFavourite.setImageResource(R.drawable.ic_pink_heart)
                    } else {
                        binding.ivMoreFavourite.setImageResource(R.drawable.ic_black_heart)
                    }
                }
            })
        }

        coroutinesLaunch(viewModel.markWatchListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data == true) {
                        binding.ivMoreWatchList.setImageResource(R.drawable.ic_orange_save)
                    } else {
                        binding.ivMoreWatchList.setImageResource(R.drawable.ic_black_save)
                    }
                }
            })
        }
    }

    private fun updateUI(stateMovieResponse: StateMovie) {
        if (stateMovieResponse.favorite == true) {
            binding.ivMoreFavourite.setImageResource(R.drawable.ic_pink_heart)
        } else {
            binding.ivMoreFavourite.setImageResource(R.drawable.ic_black_heart)
        }

        if (stateMovieResponse.watchlist == true) {
            binding.ivMoreWatchList.setImageResource(R.drawable.ic_orange_save)
        } else {
            binding.ivMoreWatchList.setImageResource(R.drawable.ic_black_save)
        }

        if (viewModel.rateValues > 0f) {
            binding.ivMoreRate.setImageResource(R.drawable.ic_yellow_rate)
        } else {
            binding.ivMoreRate.setImageResource(R.drawable.ic_black_rate)
        }
    }

    private fun canInputUser() {
        binding.llMoreFavourite.setOnSafeClick {
            viewModel.isFavourite = !viewModel.isFavourite
            viewModel.markFavourite()
        }

        binding.llMoreWatchList.setOnSafeClick {
            viewModel.isWatchList = !viewModel.isWatchList
            viewModel.markWatchList()
        }

        binding.llMoreRate.setOnSafeClick {
            Log.d("tunglvlvv", "onInitView: ${viewModel.rateValues.toInt()}")
            replaceFragmentInsideFragment(
                RateFragment.getInstance(
                    viewModel.itemMovie,
                    viewModel.rateValues.toInt()
                )
            )
        }
    }
}

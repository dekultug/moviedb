package com.example.themoviedatabase.presentation.home

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.common.extension.getFirstString
import com.example.themoviedatabase.common.getAppString
import com.example.themoviedatabase.databinding.HomeActivityBinding
import com.example.themoviedatabase.domain.model.trending.movie.TrendingResponse
import com.example.themoviedatabase.presentation.home.more.MoreFragment
import com.example.themoviedatabase.presentation.home.trendingadpter.MovieAdapter
import com.example.themoviedatabase.presentation.home.trendingadpter.PersonAdapter
import com.example.themoviedatabase.presentation.model.IViewListener
import com.example.themoviedatabase.presentation.widget.filter.FilterView
import coroutinesLaunch
import handleUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeActivity : BaseBindingActivity<HomeActivityBinding>(R.layout.home_activity) {

    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter by lazy { MovieAdapter() }

    private val tvAdapter by lazy { MovieAdapter() }

    private val personAdapter by lazy { PersonAdapter() }

    override fun getContainerId() = R.id.clRoot

    override fun onPrepareInitView() {
        super.onPrepareInitView()
        lifecycleScope.launch {
            viewModel.getMovieTrending(getAppString(R.string.day), getAppString(R.string._movie))
            delay(300)
            viewModel.getMovieTrending(getAppString(R.string.day), getAppString(R.string._tv))
            delay(300)
            viewModel.getMovieTrending(getAppString(R.string.week), getAppString(R.string._personal))
        }
    }

    override fun onInitView() {
        super.onInitView()

        binding.rvHomeMovie.adapter = movieAdapter
        binding.rvHomeMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeTv.adapter = tvAdapter
        binding.rvHomeTv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvHomeLeaderBoard.adapter = personAdapter
        binding.rvHomeLeaderBoard.layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        movieAdapter.listener = object : IMoreListener {
            override fun showMoreAction(item: TrendingResponse) {
                val moreFragment = MoreFragment.getInstance(item)
                replaceFragment(moreFragment)
            }
        }

        tvAdapter.listener = object : IMoreListener {
            override fun showMoreAction(item: TrendingResponse) {
                val moreFragment = MoreFragment.getInstance(item)
                replaceFragment(moreFragment)
            }
        }

        binding.fvHomeMovie.listener = object : FilterView.IFilterViewCallback {
            override fun onFilter(context: String) {
                binding.rvHomeMovie.smoothScrollToPosition(0)
                viewModel.getMovieTrending(context, getAppString(R.string._movie))
            }
        }

        binding.fvHomeTv.listener = object : FilterView.IFilterViewCallback {
            override fun onFilter(context: String) {
                binding.rvHomeTv.smoothScrollToPosition(0)
                viewModel.getMovieTrending(context, getAppString(R.string._tv))
            }
        }

        binding.tvHomeAccount.text = AppConfig.account?.username?.getFirstString()
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.movieTrendingState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    movieAdapter.submitList(it.data)
                }
            })
        }

        coroutinesLaunch(viewModel.tvTrendingState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    tvAdapter.submitList(it.data)
                }
            })
        }

        coroutinesLaunch(viewModel.personTrendingState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    personAdapter.submitList(it.data)
                }
            })
        }
    }
}

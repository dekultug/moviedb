package com.example.themoviedatabase.presentation.addtolist

import ai.ftech.base.eventbus.IEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.common.*
import com.example.themoviedatabase.common.event.CreateListSuccess
import com.example.themoviedatabase.databinding.AddToListActivityBinding
import com.example.themoviedatabase.domain.model.list.movie.MovieRequest
import com.example.themoviedatabase.domain.model.trending.createdlist.CreatedListResponse
import com.example.themoviedatabase.presentation.addtolist.createlist.CreateListFragment
import com.example.themoviedatabase.presentation.model.IViewListener
import com.example.themoviedatabase.presentation.widget.searchview.ISearchListener
import coroutinesLaunch
import handleUiState
import org.greenrobot.eventbus.EventBus

class AddToListActivity : BaseBindingActivity<AddToListActivityBinding>(R.layout.add_to_list_activity) {

    companion object {
        const val MOVIE_ID_KEY = "MOVIE_ID_KEY"
    }

    private val viewModel by viewModels<AddToListViewModel>()
    private val adapter by lazy { AddToListAdapter() }
    private var isShow = false

    override fun getContainerId() = R.id.clAddToList

    override fun onPrepareInitView() {
        super.onPrepareInitView()
        viewModel.getCreatedList(1)
        viewModel.movieID = intent?.getIntExtra(MOVIE_ID_KEY, 0)
        if (viewModel.movieID != null) {
            viewModel.movieRequest = MovieRequest().apply {
                mediaId = viewModel.movieID
            }
        }
    }

    override fun onInitView() {
        super.onInitView()

        binding.rvAddToListListOfYou.adapter = adapter
        binding.rvAddToListListOfYou.layoutManager = LinearLayoutManager(this)

        adapter.listener = object : AddToListAdapter.IListener {
            override fun onSelect(createdListResponse: CreatedListResponse) {
                viewModel.setItemSelect(createdListResponse)
                binding.tvAddToListSelect.enable()
                binding.tvAddToListSelect.background = getAppDrawable(R.drawable.shapre_bg_app_primary_corner_6)
                viewModel.createdListResponseSelect = createdListResponse
            }
        }

        binding.tvAddToListSelectList.setOnSafeClick {
            isShow = !isShow
            if (isShow) {
                binding.llAddToListCreated.show()
                binding.ivAddToList.gone()
            } else {
                binding.llAddToListCreated.gone()
                binding.ivAddToList.show()
            }
        }

        binding.ivAddToListClose.setOnSafeClick {
            finish()
        }

        binding.llAddToListCreate.setOnSafeClick {
            isShow = false
            binding.llAddToListCreated.gone()
            binding.ivAddToList.show()
            replaceFragment(CreateListFragment())
        }

        binding.tvAddToListSelect.setOnSafeClick {
            if (viewModel.createdListResponseSelect?.id != null && viewModel.movieRequest != null) {
                viewModel.addMovie(viewModel.createdListResponseSelect?.id!!, viewModel.movieRequest!!)
            }
        }

        binding.svbAddToList.listener = object : ISearchListener {
            override fun onTextChange(charSequence: CharSequence) {
                val list = viewModel.createdList.value.data?.toMutableList()
                val listShow = mutableListOf<CreatedListResponse>()
                list?.forEach {
                    if (it.name?.contains(charSequence) == true) {
                        listShow.add(it)
                    }
                }
                adapter.submitList(listShow)
                if (adapter.itemCount == 0) {
                    binding.tvAddToListNoData.show()
                    binding.tvAddToListNoData.text = "Chưa có list $charSequence"
                } else {
                    binding.tvAddToListNoData.gone()
                }
            }

            override fun onCloseSearch() {
                
            }
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
            is CreateListSuccess -> {
                viewModel.getCreatedList(1)
                EventBus.getDefault().removeStickyEvent(event);
            }
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.createdList) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data?.isEmpty() == true) {
                        binding.tvAddToListNoData.show()
                    } else {
                        binding.tvAddToListNoData.gone()
                    }
                    adapter.submitList(it.data)
                }
            })
        }

        coroutinesLaunch(viewModel.addMovieState) {
            handleUiState(it, object : IViewListener {

                override fun onFailure() {
                    super.onFailure()
                    Toast.makeText(this@AddToListActivity, it.throwable?.message, Toast.LENGTH_SHORT).show()
                }

                override fun onSuccess() {
                    if (it.data != null) {
                        if (it.data!!.success == true) {
                            Toast.makeText(this@AddToListActivity, "thêm vào ${viewModel.createdListResponseSelect?.name} thành công", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@AddToListActivity, it.data!!.statusMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}

package com.example.themoviedatabase.presentation.account.list

import ai.ftech.base.eventbus.IEvent
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.common.event.CreateListSuccess
import com.example.themoviedatabase.common.gone
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.common.show
import com.example.themoviedatabase.databinding.ListFragmentBinding
import com.example.themoviedatabase.presentation.account.AccountViewModel
import com.example.themoviedatabase.presentation.addtolist.createlist.CreateListFragment
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import org.greenrobot.eventbus.EventBus

class ListFragment : BaseBindingFragment<ListFragmentBinding>(R.layout.list_fragment) {

    private val viewModel by activityViewModels<AccountViewModel>()

    private val adapter by lazy { ListAdapter() }

    override fun getContainerId() = R.id.clListFragment

    override fun onInitView() {
        super.onInitView()
        binding.rvList.adapter = adapter
        binding.rvList.layoutManager = GridLayoutManager(requireContext(),2)

        binding.tvListCreate.setOnSafeClick {
            replaceFragment(CreateListFragment())
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
             //   viewModel.getCreatedList()
                EventBus.getDefault().removeStickyEvent(event);
            }
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.createdList) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        adapter.submitList(it.data)
                        if (it.data!!.isEmpty()){
                            binding.rvList.gone()
                            binding.ivListNoData.show()
                        }else{
                            binding.rvList.show()
                            binding.ivListNoData.gone()
                        }
                    }
                }
            })
        }
    }
}

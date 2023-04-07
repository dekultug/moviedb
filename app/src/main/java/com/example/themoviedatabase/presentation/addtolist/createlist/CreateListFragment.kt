package com.example.themoviedatabase.presentation.addtolist.createlist

import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingFragment
import com.example.themoviedatabase.base.eventbus.EventBusManager
import com.example.themoviedatabase.common.event.CreateListSuccess
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.databinding.CreateListFragmentBinding
import com.example.themoviedatabase.domain.model.list.createlist.CreateListRequest
import com.example.themoviedatabase.presentation.model.IViewListener
import coroutinesLaunch
import handleUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CreateListFragment : BaseBindingFragment<CreateListFragmentBinding>(R.layout.create_list_fragment) {

    private val viewModel by viewModels<CreateListViewModel>()

    override fun onInitView() {
        super.onInitView()
        binding.flCreateList.setOnSafeClick {
            backFragment()
        }

        binding.clCreateList.setOnSafeClick { }

        binding.ivCreateListClose.setOnSafeClick {
            backFragment()
        }

        binding.tvCreateListDone.setOnSafeClick {
            if (TextUtils.isEmpty(binding.edtCreateListName.text.toString())) {
                Toast.makeText(requireContext(), "Chưa có name", Toast.LENGTH_SHORT).show()
                return@setOnSafeClick
            }

            if (TextUtils.isEmpty(binding.edtCreateListDescription.text.toString())) {
                Toast.makeText(requireContext(), "Chưa có description", Toast.LENGTH_SHORT).show()
                return@setOnSafeClick
            }

            val createListRequest = CreateListRequest().apply {
                name = binding.edtCreateListName.text.toString()
                description = binding.edtCreateListDescription.text.toString()
            }

            viewModel.createList(createListRequest)
        }
    }

    override fun onObserverViewModel() {
        super.onObserverViewModel()
        coroutinesLaunch(viewModel.createListState) {
            handleUiState(it, object : IViewListener {
                override fun onSuccess() {
                    if (it.data != null) {
                        Toast.makeText(requireContext(), "Tạo list thành công", Toast.LENGTH_SHORT).show()
                        EventBusManager.instance?.postPending(CreateListSuccess())
                        lifecycleScope.launch {
                            delay(300)
                            backFragment()
                        }
                    }
                }
            })
        }
    }
}

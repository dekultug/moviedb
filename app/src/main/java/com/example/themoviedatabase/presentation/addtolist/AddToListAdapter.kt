package com.example.themoviedatabase.presentation.addtolist

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseDiffUtilCallback
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.*
import com.example.themoviedatabase.databinding.ItemOfListItemBinding
import com.example.themoviedatabase.domain.model.trending.createdlist.CreatedListResponse

class AddToListAdapter : BaseAdapter() {

    companion object {
        private const val CHANGE_STATE_SELECT_PAYLOAD = "CHANGE_STATE_SELECT_PAYLOAD"
    }

    var listener: IListener? = null

    override fun getLayoutResource(viewType: Int) = R.layout.item_of_list_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return ItemOfListVH(binding as ItemOfListItemBinding)
    }

    override fun getDiffUtil(oldList: List<Any>, newList: List<Any>): DiffUtil.Callback {
        return DiffCallback(oldList as List<CreatedListResponse>, newList as List<CreatedListResponse>)
    }

    inner class ItemOfListVH(private val binding: ItemOfListItemBinding) : BaseVH<CreatedListResponse>(binding) {

        init {
            binding.root.setOnSafeClick {
                val item = getDataAtPosition(absoluteAdapterPosition) as? CreatedListResponse
                if (item != null) {
                    listener?.onSelect(item)
                }
            }
        }

        override fun onBind(data: CreatedListResponse) {
            super.onBind(data)
            binding.tvItemOfListName.text = data.name
            binding.tvItemOfListSize.text = getAppString(R.string.size_of_list, data.itemCount.toString())
            if (data.isSelect) {
                binding.ivItemOfListSelect.show()
            } else {
                binding.ivItemOfListSelect.gone()
            }

            if (data.isInList) {
                binding.tvItemOfListName.typeface = getAppFont(R.font.svn_bold);
                binding.tvItemOfListSize.typeface = getAppFont(R.font.svn_bold);
            } else {
                binding.tvItemOfListName.typeface = getAppFont(R.font.svn_regular);
                binding.tvItemOfListSize.typeface = getAppFont(R.font.svn_regular);
            }
        }

        override fun onBind(data: CreatedListResponse, payloads: List<Any>) {
            super.onBind(data, payloads)
            payloads.forEach {
                when (it) {
                    CHANGE_STATE_SELECT_PAYLOAD -> {
                        if (data.isSelect) {
                            binding.ivItemOfListSelect.show()
                        } else {
                            binding.ivItemOfListSelect.gone()
                        }
                    }
                }
            }
        }
    }

    class DiffCallback(oldData: List<CreatedListResponse>, newData: List<CreatedListResponse>) : BaseDiffUtilCallback<CreatedListResponse>(oldData, newData) {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldData = (getOldItem(oldItemPosition) as? CreatedListResponse)
            val newData = (getNewItem(newItemPosition) as? CreatedListResponse)

            return oldData?.id == newData?.id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldData = (getOldItem(oldItemPosition) as? CreatedListResponse)
            val newData = (getNewItem(newItemPosition) as? CreatedListResponse)

            return oldData?.isSelect == newData?.isSelect
        }

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            val oldData = (getOldItem(oldItemPosition) as? CreatedListResponse)
            val newData = (getNewItem(newItemPosition) as? CreatedListResponse)

            return if (oldData?.isSelect != newData?.isSelect) {
                CHANGE_STATE_SELECT_PAYLOAD
            } else {
                null
            }
        }
    }

    interface IListener {
        fun onSelect(createdListResponse: CreatedListResponse)
    }
}

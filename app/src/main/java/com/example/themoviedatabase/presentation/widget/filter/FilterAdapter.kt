package com.example.themoviedatabase.presentation.widget.filter

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.common.*
import com.example.themoviedatabase.databinding.FilterItemBinding

class FilterAdapter : BaseAdapter() {

    companion object {
        private const val SHOW_OR_GONE_FILTER_PAYLOAD = "SHOW_OR_GONE_FILTER_PAYLOAD"
        private const val SELECT_TYPE_FILTER_PAYLOAD = "SELECT_TYPE_FILTER_PAYLOAD"
        private const val TAG = "FilterAdapter"
    }

    var listner: IFilterListener? = null

    override fun getLayoutResource(viewType: Int) = R.layout.filter_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return FilterVH(binding as FilterItemBinding)
    }

    inner class FilterVH(private val binding: FilterItemBinding) : BaseVH<FilterDisplay>(binding) {

        init {
            binding.root.setOnSafeClick {
                clickItem(absoluteAdapterPosition)
            }
        }

        override fun onBind(data: FilterDisplay) {
            super.onBind(data)
            binding.tvFilter.text = data.content

            if (data.isShow) {
                binding.llFilter.show()
                if (data.isSelect) {
                    binding.llFilter.background = getAppDrawable(R.drawable.shapre_bg_app_primary_corner_18)
                    binding.ivFilter.show()
                    //   binding.tvFilter.gradientApp(data.content)
                } else {
                    binding.ivFilter.gone()
                    binding.tvFilter.setTextColor(getAppColor(R.color.white))
                    binding.llFilter.background = null
                }
            } else {
                binding.llFilter.gone()
            }
        }

        override fun onBind(data: FilterDisplay, payloads: List<Any>) {
            super.onBind(data, payloads)

            payloads.forEach {
                Log.d(TAG, "onBind: ${it}")
                when (it) {
                    SHOW_OR_GONE_FILTER_PAYLOAD -> {
                        if (data.isShow) {
                            binding.llFilter.show()
                            if (data.isSelect) {
                                binding.llFilter.background = getAppDrawable(R.drawable.shapre_bg_app_primary_corner_18)
                                // binding.ivFilter.show()
                                binding.tvFilter.gradientApp(data.content)
                            } else {
                                binding.ivFilter.gone()
                                binding.tvFilter.setTextColor(getAppColor(R.color.white))
                                binding.llFilter.background = null
                            }
                        } else {
                            binding.llFilter.gone()
                        }
                    }
                    SELECT_TYPE_FILTER_PAYLOAD -> {
                        if (data.isSelect) {
                            binding.llFilter.background = getAppDrawable(R.drawable.shapre_bg_app_primary_corner_18)
                            binding.ivFilter.show()
                            //  binding.tvFilter.gradientApp(data.content)
                        } else {
                            binding.tvFilter.setTextColor(getAppColor(R.color.white))
                            binding.ivFilter.gone()
                            binding.llFilter.background = null
                        }
                    }
                }
            }

        }
    }

    private fun clickItem(position: Int) {
        val item = getDataAtPosition(position) as? FilterDisplay ?: return

        if (item.isShow) {
            // show all
            dataList.forEachIndexed { index, any ->
                val itemHide = any as? FilterDisplay
                var positionItemHide = -1
                if (itemHide != null && itemHide != item && !itemHide.isShow) {
                    itemHide.isShow = true
                    positionItemHide = index
                    notifyItemChanged(positionItemHide, SHOW_OR_GONE_FILTER_PAYLOAD)
                }
            }
        } else {
            // gone all - item
            dataList.forEachIndexed { index, any ->
                val itemShow = any as? FilterDisplay
                var positionItemShow = -1
                if (itemShow != null && itemShow != item && itemShow.isShow) {
                    itemShow.isShow = false
                    positionItemShow = index
                    notifyItemChanged(positionItemShow, SHOW_OR_GONE_FILTER_PAYLOAD)
                }
            }
        }
        item.isShow = !item.isShow
        if (!item.isSelect) {
            item.isSelect = true
            item.isShow = true
            notifyItemChanged(position, SELECT_TYPE_FILTER_PAYLOAD)

            // set select all - item false
            dataList.forEachIndexed { index, any ->
                val itemSelect = any as? FilterDisplay
                var positionItemSelect = -1
                if (itemSelect != null && itemSelect != item && itemSelect.isSelect) {
                    itemSelect.isSelect = false
                    positionItemSelect = index
                    notifyItemChanged(positionItemSelect, SELECT_TYPE_FILTER_PAYLOAD)
                }
            }

            // gone all - item
            dataList.forEachIndexed { index, any ->
                val itemShow = any as? FilterDisplay
                var positionItemShow = -1
                if (itemShow != null && itemShow != item && itemShow.isShow) {
                    itemShow.isShow = false
                    positionItemShow = index
                    notifyItemChanged(positionItemShow, SHOW_OR_GONE_FILTER_PAYLOAD)
                }
            }
            if (item.content != null) {
                listner?.onFilter(item.content!!)
            }
        }
    }
}

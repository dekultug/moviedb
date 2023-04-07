package com.example.themoviedatabase.presentation.widget.filter

import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.getAppString

class FilterDisplayImpl : IFilterDisplay {
    override fun getListFilterDisplay(type: FILTER_TYLE): List<FilterDisplay> {
        val list = arrayListOf<FilterDisplay>()

        when(type){
            FILTER_TYLE.TRENDING->{
                list.add(FilterDisplay(isSelect = true, isShow = true).apply {
                    content = getAppString(R.string.day)
                })

                list.add(FilterDisplay(isSelect = false, isShow = false).apply {
                    content = getAppString(R.string.week)
                })
            }

            FILTER_TYLE.ACCOUNT->{
                list.add(FilterDisplay(isSelect = true, isShow = true).apply {
                    content = getAppString(R.string._movie)
                })

                list.add(FilterDisplay(isSelect = false, isShow = false).apply {
                    content = getAppString(R.string._tv)
                })
            }
        }

        return list
    }
}

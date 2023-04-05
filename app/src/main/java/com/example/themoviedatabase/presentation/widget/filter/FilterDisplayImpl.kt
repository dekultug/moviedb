package com.example.themoviedatabase.presentation.widget.filter

import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.getAppString

class FilterDisplayImpl : IFilterDisplay {
    override fun getListFilterDisplay(): List<FilterDisplay> {
        val list = arrayListOf<FilterDisplay>()

        list.add(FilterDisplay(isSelect = true, isShow = true).apply {
            content = getAppString(R.string.day)
        })

        list.add(FilterDisplay(isSelect = false, isShow = false).apply {
            content = getAppString(R.string.week)
        })
        return list
    }
}

package com.example.themoviedatabase.presentation.widget.filter

enum class FILTER_TYLE(var value: Int) {
    MOVIE(0), TV(1), PERSON(2);

    companion object {
        fun valueOfName(type: Int): FILTER_TYLE? {
            val item = FILTER_TYLE.values().find {
                it.value == type
            }
            return item
        }
    }
}

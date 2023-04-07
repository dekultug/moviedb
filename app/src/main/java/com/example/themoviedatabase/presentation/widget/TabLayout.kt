package com.example.themoviedatabase.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.themoviedatabase.R
import com.example.themoviedatabase.common.setOnSafeClick


class TabLayout constructor(ctx: Context, attrs: AttributeSet?) : LinearLayout(ctx, attrs) {

    private var tab1: TextView? = null
    private var tab2: TextView? = null
    private var tab3: TextView? = null
    private var tab4: TextView? = null
    private var tabSelect: TextView? = null
    private var index = -1

    var listener: IListener? = null

    init {
        LayoutInflater.from(ctx).inflate(R.layout.tab_layout, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        tab1 = findViewById(R.id.tab1)
        tab2 = findViewById(R.id.tab2)
        tab3 = findViewById(R.id.tab3)
        tab4 = findViewById(R.id.tab4)
        tabSelect = findViewById(R.id.textSelected)

        tab1?.setOnSafeClick {
            if (index != 1) {
                tab1?.setTextColor(getResources().getColor(R.color.white));
                tab2?.setTextColor(getResources().getColor(R.color.app_primary));
                tab3?.setTextColor(getResources().getColor(R.color.app_primary));
                tab4?.setTextColor(getResources().getColor(R.color.app_primary));
                tabSelect?.animate()?.x(0f)?.setDuration(60);
                listener?.onTab1()
            }
            index = 1
        }

        tab2?.setOnSafeClick {
            if (index != 2){
                tab1?.setTextColor(resources.getColor(R.color.app_primary))
                tab2?.setTextColor(resources.getColor(R.color.white))
                tab3?.setTextColor(resources.getColor(R.color.app_primary))
                tab4?.setTextColor(resources.getColor(R.color.app_primary))
                val size = tab2!!.width.toFloat()
                tabSelect?.animate()?.x(size)?.setDuration(60)
                listener?.onTab2()
            }
            index = 2
        }

        tab3?.setOnSafeClick {
            if (index != 3){
                tab1?.setTextColor(resources.getColor(R.color.app_primary))
                tab2?.setTextColor(resources.getColor(R.color.app_primary))
                tab3?.setTextColor(resources.getColor(R.color.white))
                tab4?.setTextColor(resources.getColor(R.color.app_primary))
                val size = tab3!!.width.toFloat() * 2
                tabSelect?.animate()?.x(size)?.setDuration(60)
                listener?.onTab3()
            }
            index = 3
        }

        tab4?.setOnSafeClick {
           if(index != 4){
               tab1?.setTextColor(resources.getColor(R.color.app_primary))
               tab2?.setTextColor(resources.getColor(R.color.app_primary))
               tab3?.setTextColor(resources.getColor(R.color.app_primary))
               tab4?.setTextColor(resources.getColor(R.color.white))
               val size = tab4!!.width.toFloat() * 3
               tabSelect?.animate()?.x(size)?.setDuration(60)
               listener?.onTab4()
           }
            index = 4
        }
    }

    fun setTab1(){

    }

    interface IListener {
        fun onTab1()
        fun onTab2()
        fun onTab3()
        fun onTab4()
    }
}

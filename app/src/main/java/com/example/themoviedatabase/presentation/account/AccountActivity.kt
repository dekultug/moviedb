package com.example.themoviedatabase.presentation.account

import ai.ftech.base.eventbus.IEvent
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.themoviedatabase.AppConfig
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.component.BaseBindingActivity
import com.example.themoviedatabase.common.event.AddToListSuccess
import com.example.themoviedatabase.common.event.CreateListSuccess
import com.example.themoviedatabase.common.event.CreateListSuccessVersion2
import com.example.themoviedatabase.common.extension.getFirstString
import com.example.themoviedatabase.common.setOnSafeClick
import com.example.themoviedatabase.databinding.AccountActivityBinding
import com.example.themoviedatabase.presentation.account.favoutite.FavouriteFragment
import com.example.themoviedatabase.presentation.account.list.ListFragment
import com.example.themoviedatabase.presentation.account.rate.RateFragment
import com.example.themoviedatabase.presentation.account.watchlist.WatchListFragment
import com.example.themoviedatabase.presentation.widget.TabLayout
import org.greenrobot.eventbus.EventBus

class AccountActivity : BaseBindingActivity<AccountActivityBinding>(R.layout.account_activity) {

    private val viewModel by viewModels<AccountViewModel>()

    private var adapter: ViewPagerAdapter? = null

    override fun getContainerId() = R.id.clAccount

    override fun onPrepareInitView() {
        super.onPrepareInitView()
        viewModel.getCreatedList()
        viewModel.getFavouriteMovieList()
        viewModel.getWatchList()
        viewModel.getRateList()
    }

    override fun onInitView() {
        super.onInitView()

        binding.ivAccountHeaderBack.setOnSafeClick {
            finish()
        }

        adapter = ViewPagerAdapter(this, ArrayList<Fragment>().apply {
            add(ListFragment())
            add(FavouriteFragment())
            add(WatchListFragment())
            add(RateFragment())
        })

        binding.vpAccount.adapter = adapter
        binding.vpAccount.isUserInputEnabled = false

        binding.tvAccountName.text = (AppConfig.account?.username)
        binding.tvAccount.text = AppConfig.account?.username?.getFirstString()

        binding.tbAccount.listener = object : TabLayout.IListener {
            override fun onTab1() {
                binding.vpAccount.setCurrentItem(0, true)
            }

            override fun onTab2() {
                binding.vpAccount.setCurrentItem(1, true)
            }

            override fun onTab3() {
                binding.vpAccount.setCurrentItem(2, true)
            }

            override fun onTab4() {
                binding.vpAccount.setCurrentItem(3, true)
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
        Log.d(TAG, "onEvent: $event")
        when (event) {
            is AddToListSuccess -> {
                viewModel.getCreatedList()
                EventBus.getDefault().removeStickyEvent(event);
            }
            is CreateListSuccessVersion2 -> {
                viewModel.getCreatedList()
                EventBus.getDefault().removeStickyEvent(event);
            }
        }
    }
}

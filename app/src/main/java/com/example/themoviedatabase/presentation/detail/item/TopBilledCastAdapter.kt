package com.example.themoviedatabase.presentation.detail.item

import androidx.databinding.ViewDataBinding
import com.example.themoviedatabase.R
import com.example.themoviedatabase.base.adapter.BaseAdapter
import com.example.themoviedatabase.base.adapter.BaseVH
import com.example.themoviedatabase.databinding.ActorItemBinding
import com.example.themoviedatabase.domain.model.trending.detail.actor.Cast
import loadImage

class TopBilledCastAdapter : BaseAdapter() {
    override fun getLayoutResource(viewType: Int) = R.layout.actor_item

    override fun onCreateViewHolder(viewType: Int, binding: ViewDataBinding): BaseVH<*>? {
        return ActorVH(binding as ActorItemBinding)
    }

    inner class ActorVH(private val binding: ActorItemBinding) : BaseVH<Cast>(binding) {

        override fun onBind(data: Cast) {
            super.onBind(data)
            binding.ivActorAvatar.loadImage(data.getAvatar())
            binding.tvActorName.text = data.originalName
            binding.tvActorCharacter.text = data.character
        }
    }
}

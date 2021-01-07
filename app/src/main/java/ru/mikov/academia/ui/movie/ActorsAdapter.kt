package ru.mikov.academia.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.mikov.academia.R
import ru.mikov.academia.data.local.Actor
import ru.mikov.academia.databinding.ItemActorBinding

class ActorsAdapter : ListAdapter<Actor, ActorsAdapter.ActorHolder>(ActorsDiffCallBack()) {

    lateinit var viewBinding: ItemActorBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        viewBinding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ActorHolder(private val binding: ItemActorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: Actor) {
            with(binding) {
                Glide.with(itemView)
                    .load(actor.picture)
                    .fallback(R.drawable.ic_baseline_photo_24)
                    .transform(CenterCrop(), RoundedCorners(8))
                    .into(ivActor)
                tvActorName.text = actor.name
            }
        }
    }

    class ActorsDiffCallBack : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem == newItem
    }
}
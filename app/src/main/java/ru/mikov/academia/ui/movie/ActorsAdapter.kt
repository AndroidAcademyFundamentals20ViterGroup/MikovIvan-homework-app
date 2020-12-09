package ru.mikov.academia.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.mikov.academia.R
import ru.mikov.academia.data.Actor

class ActorsAdapter : ListAdapter<Actor, ActorsAdapter.ActorHolder>(ActorsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        return ActorHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ActorHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val actorPoster: ImageView = itemView.findViewById(R.id.iv_actor)
        private val actorName: TextView = itemView.findViewById(R.id.tv_actor_name)

        fun bind(actor: Actor) {
            Glide.with(itemView)
                .load(actor.picture)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(actorPoster)
            actorName.text = actor.name
        }
    }

    class ActorsDiffCallBack : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Actor, newItem: Actor): Boolean =
            oldItem == newItem
    }
}
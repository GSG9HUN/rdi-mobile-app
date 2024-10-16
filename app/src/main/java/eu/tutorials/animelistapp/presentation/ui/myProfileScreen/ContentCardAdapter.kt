package eu.tutorials.animelistapp.presentation.ui.myProfileScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.tutorials.animelistapp.R
import eu.tutorials.animelistapp.domain.model.myFavouriteList.ContentItem
import eu.tutorials.animelistapp.domain.model.myFavouriteList.anime.MyFavouriteAnime
import eu.tutorials.animelistapp.domain.model.myFavouriteList.manga.MyFavouriteManga
import eu.tutorials.animelistapp.presentation.ui.Screen


class ContentCardAdapter(
    private val onClicked: (String) -> Unit,
) : ListAdapter<ContentItem, ContentCardAdapter.ViewHolder>(UserDiffCallBack()) {

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val episodesChaptersTextView = itemView.findViewById<TextView>(R.id.episodesTextView)

        @SuppressLint("SetTextI18n")
        fun bind(item: ContentItem, onClicked: (String) -> Unit) {
            titleTextView.text = item.title
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(imageView)
            if (item is MyFavouriteAnime) {
                episodesChaptersTextView.text = "Episodes: ${item.episode}"
                itemView.setOnClickListener {
                    onClicked(Screen.AnimeDetails.route + "/${item.id}")
                }
            } else if (item is MyFavouriteManga) {
                episodesChaptersTextView.text = "Chapters: ${item.chapter}"
                itemView.setOnClickListener {
                    onClicked(Screen.MangaDetails.route + "/${item.id}")
                }
            }


        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<ContentItem>() {
        override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ContentItem, newItem: ContentItem): Boolean {
            val contentsAreTheSame =
                oldItem.title == newItem.title && oldItem.imageUrl == newItem.imageUrl
            return if (oldItem is MyFavouriteAnime && newItem is MyFavouriteAnime) {
                oldItem.episode == newItem.episode && contentsAreTheSame
            } else if (oldItem is MyFavouriteManga && newItem is MyFavouriteManga) {
                oldItem.chapter == newItem.chapter && contentsAreTheSame
            } else false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClicked)
    }
}




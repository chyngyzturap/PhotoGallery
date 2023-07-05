package com.pharos.photogallery.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pharos.photogallery.databinding.ItemGalleryBinding
import com.pharos.photogallery.domain.model.Photo

class GalleryAdapter(private val listener: PhotoClickListener
) : ListAdapter<Photo, GalleryAdapter.ViewHolderCars>(DIFF) {

    private fun getItemAtPos(position: Int): Photo {
        return getItem(position)
    }

    private var _binding: ItemGalleryBinding? = null

    inner class ViewHolderCars(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val current = getItemAtPos(position)
            binding.ivPhoto.load(current.urls.regular)
            binding.root.setOnClickListener {
                listener.onPhotoClick(current.urls.regular)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCars {
        _binding = ItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderCars(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolderCars, position: Int) {
        holder.onBind(position)
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(
                oldItem: Photo,
                newItem: Photo
            ): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(
                oldItem: Photo,
                newItem: Photo
            ): Boolean {
                return oldItem.alt_description == oldItem.alt_description
            }
        }
    }

    interface PhotoClickListener {
        fun onPhotoClick(uri: String)
    }

}
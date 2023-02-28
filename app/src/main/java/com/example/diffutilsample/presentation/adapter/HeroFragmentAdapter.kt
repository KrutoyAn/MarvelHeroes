package com.example.diffutilsample.presentation.adapter

import HeroDiffutil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.R
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class HeroFragmentAdapter() : RecyclerView.Adapter<HeroFragmentAdapter.HeroFragmentViewHolder>() {

    var heroesList = emptyList<HeroModel>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroFragmentViewHolder {
        return HeroFragmentAdapter.HeroFragmentViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroFragmentViewHolder, position: Int) {
        val item = heroesList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    class HeroFragmentViewHolder(
        private val binding: HeroItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HeroModel) {

            binding.heroTitle.text = item.name
            Glide.with(binding.heroImage.context).load(item.thumbnail.getImageUrl())
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0)))
                .into(binding.heroImage)

        }
    }
}
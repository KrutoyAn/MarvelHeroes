package com.example.diffutilsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.R
import com.example.diffutilsample.data.dto.MarvelPagingDto
import com.example.diffutilsample.data.dto.heroinfo.HeroResponse
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class ComicsAdapter() : PagingDataAdapter<HeroModel, ComicsAdapter.HeroesViewHolder>(HeroDiffutil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.setIsRecyclable(false)
    }

    class HeroesViewHolder(
        private val binding: HeroItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}
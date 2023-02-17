package com.example.diffutilsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.R
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

typealias Listener = (position: Long) -> Unit

class HeroesAdapter() : PagingDataAdapter<HeroModel, HeroesAdapter.HeroesViewHolder>(HeroDiffutil) {

    var mListener: Listener = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item, mListener)
    }

    class HeroesViewHolder(
        private val binding: HeroItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeroModel, clickListener: Listener) {
            val anim =
                AnimationUtils.loadAnimation(binding.cardView.context, R.anim.fade_out).apply {
                    interpolator = AnticipateOvershootInterpolator()
                    setAnimationListener(object : AnimationListener {
                        override fun onAnimationStart(animation: Animation?) {
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            clickListener.invoke(item.id)
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                        }

                    })
                }

            binding.cardView.setOnClickListener { clickListener.invoke(item.id) }
            binding.heroTitle.text = item.name
            Glide.with(binding.heroImage.context).load(item.thumbnail.getImageUrl())
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0)))
                .into(binding.heroImage)

            binding.cardView.setOnClickListener {
                binding.cardView.startAnimation(anim)
            }
        }

    }
}
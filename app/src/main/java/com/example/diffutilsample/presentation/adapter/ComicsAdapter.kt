package com.example.diffutilsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateInterpolator
import android.view.animation.AnticipateOvershootInterpolator
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.R
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.adapter.ComicsAdapter.ComicsViewHolder
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import com.example.diffutilsample.presentation.model.СomicsModel
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


class ComicsAdapter() : RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {

    var mListener: Listener = {}
    var heroesList = emptyList<СomicsModel>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        return ComicsAdapter.ComicsViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }
    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val item = heroesList[position]
        holder.bind(item, mListener)
    }
    fun setData(newHeroes: List<СomicsModel>) {
        val diffUtil = ComicsDiffUtil(heroesList, newHeroes)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        heroesList = newHeroes
        diffResults.dispatchUpdatesTo(this)

    }

    class ComicsViewHolder(
        private val binding: HeroItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: СomicsModel, clickListener: Listener) {
            val anim =
                AnimationUtils.loadAnimation(binding.cardView.context, R.anim.error_fade_out,)
                    .apply {
                        interpolator = AnticipateInterpolator()
                        setAnimationListener(object : Animation.AnimationListener {
                            override fun onAnimationStart(animation: Animation?) {
                            }

                            override fun onAnimationEnd(animation: Animation?) {
                                clickListener.invoke(item.id)
                            }

                            override fun onAnimationRepeat(animation: Animation?) {
                            }

                        })
                    }

            fun bind(item: СomicsModel) {
                binding.heroTitle.text = item.name
                Glide.with(binding.heroImage.context).load(item.thumbnail.path)
                    .load(item.thumbnail.extension)
                    .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0)))
                    .into(binding.heroImage)

                binding.cardView.setOnClickListener {
                    binding.cardView.startAnimation(anim)
                }
            }

        }
    }
}
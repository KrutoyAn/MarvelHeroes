package com.example.diffutilsample.presentation.adapter

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.R
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import com.example.diffutilsample.presentation.viewmodel.HeroesViewModel
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.util.ArrayList

typealias Listener = (position: Long) -> Unit

class HeroesAdapter() : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {

    var mListener: Listener = {}
    var heroesList = emptyList<HeroModel>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesViewHolder {
        return HeroesViewHolder(
            HeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroesViewHolder, position: Int) {
        val item = heroesList[position]
        holder.bind(item, mListener)
    }


    override fun getItemCount(): Int {
        return heroesList.size
    }

    //adapter.setData(searchList)

    fun setData(newHeroes: List<HeroModel>) {
        val diffUtil = HeroDiffutil(heroesList, newHeroes)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        heroesList = newHeroes
        diffResults.dispatchUpdatesTo(this)
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
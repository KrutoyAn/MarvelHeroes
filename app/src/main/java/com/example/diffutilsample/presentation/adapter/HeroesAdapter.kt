package com.example.diffutilsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.diffutilsample.databinding.HeroItemBinding
import com.example.diffutilsample.presentation.model.HeroModel
import com.example.diffutilsample.presentation.model.getImageUrl
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class HeroesAdapter : RecyclerView.Adapter<HeroesAdapter.HeroesViewHolder>() {


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
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return heroesList.size
    }

    fun setData(newHeroes: List<HeroModel>) {
        val diffUtil = HeroDiffutil(heroesList, newHeroes)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        heroesList = newHeroes
        diffResults.dispatchUpdatesTo(this)
    }

    class HeroesViewHolder(private val binding: HeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeroModel) {
            binding.heroTitle.text = item.name
            Glide.with(binding.heroImage.context)
                .load(item.thumbnail.getImageUrl())
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(45, 0)))
                .into(binding.heroImage)

        }
    }
}
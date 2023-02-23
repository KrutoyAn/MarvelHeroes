package com.example.diffutilsample.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.diffutilsample.presentation.model.HeroModel

object HeroDiffutil : DiffUtil.ItemCallback<HeroModel>() {
    override fun areItemsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        // Id is unique.
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: HeroModel, newItem: HeroModel): Boolean {
        return oldItem == newItem
    }
}
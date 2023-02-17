package com.example.diffutilsample.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.diffutilsample.presentation.model.HeroModel


class ComicsDiffUtil(
    private val oldList: List<HeroModel>,
    private val newList: List<HeroModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }


    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> false
            oldList[oldItemPosition].name != newList[newItemPosition].name -> false
            else -> true
        }
    }
}
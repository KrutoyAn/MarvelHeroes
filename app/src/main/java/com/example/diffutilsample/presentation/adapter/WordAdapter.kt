package com.example.diffutilsample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsample.databinding.RowItemBinding
import com.example.diffutilsample.model.Word

class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    var trashAction: (id: Int) -> Unit = {}

    var wordList = emptyList<Word>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = wordList[position]
        holder.bind(item, trashAction)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }


    fun setData(newWordList: List<Word>) {
        val diffUtil = WordDiffUtil(wordList, newWordList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        wordList = newWordList
        diffResults.dispatchUpdatesTo(this)
    }

    class WordViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Word, trashAction: (id: Int) -> Unit) {
            val itemId = item.id
            binding.textViewWord.text = item.word
            binding.imageTrash.setOnClickListener { trashAction.invoke(itemId) }
        }
    }
}
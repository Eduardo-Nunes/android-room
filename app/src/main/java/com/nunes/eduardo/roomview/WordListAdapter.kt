package com.nunes.eduardo.roomview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view_item.view.*

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords: List<Word>? = null // Cached copy of words


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mWords?.size ?: 0
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (itemCount > 0) {
            mWords?.get(position)?.let { holder.onBind(it) }
        } else {
            holder.noData()
        }
    }

    fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(word: Word) = with(itemView) {
            textView.text = word.mWord
        }

        fun noData() = with(itemView) {
            textView.text = "No word added yet"
        }
    }
}
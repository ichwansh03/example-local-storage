package com.ichwan.samples.roomdb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ichwan.samples.roomdb.databinding.AdapterMainBinding
import com.ichwan.samples.roomdb.models.Note

class NoteAdapter(private val note: ArrayList<Note>, private val listener: OnAdapterListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    inner class ViewHolder (var binding: AdapterMainBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = note.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = note[position]
        holder.binding.apply {
            textTitle.text = item.title
            textTitle.setOnClickListener {
                listener.onClick(item)
            }
            iconEdit.setOnClickListener {
                listener.onUpdate(item)
            }
            iconDelete.setOnClickListener {
                listener.onDelete(item)
            }
        }
    }

    fun setData(list: List<Note>){
        note.clear()
        note.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(note: Note)
        fun onUpdate(note: Note)
        fun onDelete(note: Note)
    }
}
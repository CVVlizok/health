package com.example.health.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.health.R

class EntryAdapter : ListAdapter<HealthParameterEntry, EntryAdapter.EntryViewHolder>(DiffCallback()) {

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val valueText: TextView = itemView.findViewById(R.id.entryValue)
        private val timestampText: TextView = itemView.findViewById(R.id.entryTimestamp)

        fun bind(entry: HealthParameterEntry) {
            valueText.text = entry.value
            timestampText.text = entry.timestamp
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parameter_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<HealthParameterEntry>() {
        override fun areItemsTheSame(oldItem: HealthParameterEntry, newItem: HealthParameterEntry) =
            oldItem.timestamp == newItem.timestamp

        override fun areContentsTheSame(oldItem: HealthParameterEntry, newItem: HealthParameterEntry) =
            oldItem == newItem
    }
}

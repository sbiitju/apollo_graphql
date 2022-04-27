package com.shahinbasahr.apollo.getitem.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shahinbasahr.apollo.R
import com.shahinbasahr.apollo.getitem.model.Item

class GetItemAdapter : PagingDataAdapter<Item, GetItemAdapter.ItemViewHolder>(DataDifferntiator) {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview = view.findViewById<TextView>(R.id.textViewName)
        val textview2 = view.findViewById<TextView>(R.id.textViewEmail)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textview.text = getItem(position)?.meta?.name
        holder.textview2.text = getItem(position)?.basePrice.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    object DataDifferntiator : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}
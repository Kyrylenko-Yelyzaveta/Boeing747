package com.flyinthesky.boeing747.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.DrawerItemBinding
import com.flyinthesky.boeing747.dto.BoeingItem

class DrawerAdapter(
    private val itemList: MutableList<BoeingItem>,
    private val listener: OnItemClickListener? = null
) : RecyclerView.Adapter<DrawerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: BoeingItem)
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = DrawerItemBinding.bind(item)
        fun bind(boeing: BoeingItem) = with(binding) {
            txtDrawer.text = boeing.mainText
            binding.root.setOnClickListener {
                listener?.onItemClick(boeing)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.drawer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateItems(boeing: List<BoeingItem>) {
        itemList.clear()
        itemList.addAll(boeing)
        notifyDataSetChanged()
    }


}
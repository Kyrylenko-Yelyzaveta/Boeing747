package com.flyinthesky.boeing747.screens.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.flyinthesky.boeing747.R
import com.flyinthesky.boeing747.databinding.BoeingItemBinding
import com.flyinthesky.boeing747.dto.BoeingItem
import kotlin.coroutines.coroutineContext


class BoeingAdapter(
    private val listener: OnItemClickListener? = null
) : RecyclerView.Adapter<BoeingAdapter.BoeingHolder>() {
    private val boeingList = ArrayList<BoeingItem>()

    interface OnItemClickListener {
        fun onItemClick(item: BoeingItem)
    }

    inner class BoeingHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = BoeingItemBinding.bind(item)
        fun bind(boeing: BoeingItem) = with(binding) {
            imView.setImageResource(boeing.imageView)
            txtDefaultText.text = boeing.defaultText
            txtMainText.text = boeing.mainText
            binding.root.setOnClickListener {
                animateItem(boeing)
            }
        }

        fun animateItem(boeing: BoeingItem) {
            val buttonAnimation = AnimationUtils.loadAnimation(itemView.context, R.anim.item_anim)
            buttonAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    listener?.onItemClick(boeing)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            binding.root.startAnimation(buttonAnimation)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoeingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boeing_item, parent, false)
        return BoeingHolder(view)
    }

    override fun onBindViewHolder(holder: BoeingHolder, position: Int) {
        val data = boeingList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return boeingList.size
    }

    fun addBoeing(boeing: List<BoeingItem>) {
        boeingList.clear()
        boeingList.addAll(boeing)
        notifyDataSetChanged()
    }
}
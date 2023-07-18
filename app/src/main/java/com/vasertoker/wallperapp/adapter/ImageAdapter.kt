package com.vasertoker.wallperapp.adapter

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vasertoker.wallperapp.databinding.ItemLayoutBinding
import com.vasertoker.wallperapp.models.Result

class ImageAdapter(private val list: List<Result>, val context : Context) : RecyclerView.Adapter<ImageAdapter.Vh>(){

    private var onItemClick: OnItemClick? = null
    inner class Vh(val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun onBind(result: Result) {

            Glide.with(context).load(result.urls.small).into(binding.image)
            Log.i(TAG, "onBind: ${result.urls.small}")
            binding.root.setOnClickListener {
                if (onItemClick != null) {
                    onItemClick?.onClick(result)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }


    interface OnItemClick {
        fun onClick(result: com.vasertoker.wallperapp.models.Result)
    }

    fun setOnItemClick(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }
}
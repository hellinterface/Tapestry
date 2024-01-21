package com.msvteam.tapestry.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.databinding.RecyclerviewItemBoxBinding
import com.msvteam.tapestry.databinding.RecyclerviewItemModelBinding
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.model.Model

class BoxRecyclerAdapter(private val boxList: List<Box>, private var onClickListener: OnClickListener? = null, private var onLongClickListener: OnClickListener? = null) : RecyclerView.Adapter<BoxRecyclerAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewItemBoxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(boxModel: Box, view: ViewHolder) {
            binding.textViewTitle.text = boxModel.name
        }
    }

    // Функция, срабатывающая при нажатии на элемент списка.
    fun interface OnClickListener {
        fun onClick(position: Int, model: Box)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerviewItemBoxBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(boxList[position], viewHolder)
        viewHolder.itemView.setOnClickListener {
            Log.d("DEBUG", "CLICKED")
            if (onClickListener != null) {
                onClickListener!!.onClick(position, boxList[position])
            }
        }
        viewHolder.itemView.setOnLongClickListener {
            if (onLongClickListener != null) {
                onLongClickListener!!.onClick(position, boxList[position])
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return boxList.size
    }
}
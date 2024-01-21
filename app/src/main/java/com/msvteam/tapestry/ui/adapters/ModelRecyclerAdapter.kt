package com.msvteam.tapestry.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.databinding.RecyclerviewItemModelBinding
import com.msvteam.tapestry.domain.model.Model

class ModelRecyclerAdapter(private val modelList: List<Model>) : RecyclerView.Adapter<ModelRecyclerAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewItemModelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modelObject: Model, view: ViewHolder) {
            binding.textViewModel.text = modelObject.title + "(" + modelObject.id.toString() + ")"
            binding.textViewLength.text = modelObject.time.toString() + " минут"
            binding.textViewTapeType.text = "Тип " + modelObject.tapeType.toString()
        }
    }

    // Функция, срабатывающая при нажатии на элемент списка.
    private var onClickListener: OnClickListener? = null
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    fun interface OnClickListener {
        fun onClick(position: Int, model: Model)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerviewItemModelBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(modelList[position], viewHolder)
        viewHolder.itemView.setOnClickListener {
            Log.d("DEBUG", "CLICKED")
            if (onClickListener != null) {
                onClickListener!!.onClick(position, modelList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
}
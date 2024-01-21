package com.msvteam.tapestry.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.data.ModelRepositoryImpl
import com.msvteam.tapestry.databinding.RecyclerviewItemCassetteBinding
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.model.GetModelUseCase
import com.msvteam.tapestry.domain.model.Model

class CassetteRecyclerAdapter(private val cassetteList: List<Cassette>) : RecyclerView.Adapter<CassetteRecyclerAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewItemCassetteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tapeObject: Cassette) {
            binding.textViewTitle.text = tapeObject.title
            binding.textViewCid.text = tapeObject.cid
            binding.textViewRatingPhysical.text = "Состояние: " + (tapeObject.ratingPhysical+1)
            binding.textViewRatingAudio.text = "Качество звучания: " + (tapeObject.ratingAudio+1)
            if (tapeObject.modelId != null) {
                val model = GetModelUseCase(ModelRepositoryImpl).getModel(tapeObject.modelId!!)
                if (model.value != null) {
                    binding.textViewModel.text = "Модель: " + model.value!!.title
                    binding.textViewLength.text = "Длина: " + model.value!!.time + " минут"
                    binding.textViewTapeType.text = "Тип ленты: " + model.value!!.tapeType
                }
            }
        }
    }

    // Функция, срабатывающая при нажатии на элемент списка.
    private var onClickListener: OnClickListener? = null
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    fun interface OnClickListener {
        fun onClick(position: Int, cassette: Cassette)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerviewItemCassetteBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(cassetteList[position])
        // Поставить обработчик клика
        viewHolder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, cassetteList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return cassetteList.size
    }
}
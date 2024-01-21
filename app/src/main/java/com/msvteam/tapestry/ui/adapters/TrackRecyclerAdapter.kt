package com.msvteam.tapestry.ui.adapters

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.data.TrackRepositoryImpl
import com.msvteam.tapestry.data.TracklistRepositoryImpl
import com.msvteam.tapestry.databinding.RecyclerviewItemTrackBinding
import com.msvteam.tapestry.domain.track.DeleteTrackUseCase
import com.msvteam.tapestry.domain.track.Track

class TrackRecyclerAdapter(private val trackList: MutableList<Track>, private val context: Context, private val onTrackDelete: OnTrackDelete) : RecyclerView.Adapter<TrackRecyclerAdapter.ViewHolder>() {
    class ViewHolder(private val binding: RecyclerviewItemTrackBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trackObject: Track, onTrackDelete: OnTrackDelete) {
            binding.textInputLayoutTitle.editText!!.setText(trackObject.title)
            binding.textInputLayoutArtist.editText!!.setText(trackObject.artist)
            // Слушать изменения текстовых полей
            binding.textInputLayoutTitle.editText!!.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    trackObject.title = s.toString()
                }
            })
            binding.textInputLayoutArtist.editText!!.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    trackObject.artist = s.toString()
                }
            })
            binding.button.setOnClickListener {
                Log.d("DEBUG", "DELETING TRACK")
                onTrackDelete.onDelete(trackObject)
            }
        }
    }

    fun interface OnTrackDelete {
        fun onDelete(trackObject: Track)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = RecyclerviewItemTrackBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(view, context)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(trackList[position], onTrackDelete)
        /*
        viewHolder.itemView.setOnLongClickListener { view ->
            //Log.d("DEBUG", "CLICKED")
            //trackList.removeAt(position)
        }*/
    }

    override fun getItemCount(): Int {
        return trackList.size
    }
}
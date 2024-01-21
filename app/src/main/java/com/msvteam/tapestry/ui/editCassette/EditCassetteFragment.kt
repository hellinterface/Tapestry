package com.msvteam.tapestry.ui.editCassette

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.msvteam.tapestry.data.TrackRepositoryImpl
import com.msvteam.tapestry.databinding.FragmentEditCassetteBinding
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.track.DeleteTrackUseCase
import com.msvteam.tapestry.domain.track.Track
import com.msvteam.tapestry.ui.adapters.CassetteRecyclerAdapter
import com.msvteam.tapestry.ui.adapters.TrackRecyclerAdapter
import com.msvteam.tapestry.ui.boxList.BoxListActivity
import com.msvteam.tapestry.ui.modelList.ModelListActivity


class EditCassetteFragment() : Fragment() {

    private var _binding: FragmentEditCassetteBinding? = null
    private var parentActivity : EditCassetteActivity? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditCassetteViewModel

    fun setParentActivity(activity : EditCassetteActivity) {
        parentActivity = activity
    }

    private var cassetteId: Long? = null
    val recyclerViewItemClickListener: CassetteRecyclerAdapter.OnClickListener? = null

    fun setCassetteId(id: Long?) {
        cassetteId = id
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EditCassetteViewModel::class.java]
        _binding = FragmentEditCassetteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val minusOne: Long = -1
        Log.d("CASSETTE_ID", cassetteId.toString())
        if (cassetteId != null && cassetteId != minusOne) {
            viewModel.setCassette(cassetteId!!)
            viewModel.cassetteObject.observe(viewLifecycleOwner) {
                Log.d("DEBUG", "CASSETTE OBJECT OBSERVER ##############################")
                if (it != null) {
                    Log.d("DEBUG", it.toString())
                    if (it.id != null && it.id != minusOne) {
                        viewModel.refreshTracklists()
                    }
                }
            }
        }

        // Список для стороны А
        binding.recyclerViewSideA.layoutManager = LinearLayoutManager(this.context)
        viewModel.trackList_sideA.observe(viewLifecycleOwner) {
            val onTrackDelete = TrackRecyclerAdapter.OnTrackDelete { track ->
                if (track.id != null) {
                    val deleteTrackUseCase = DeleteTrackUseCase(TrackRepositoryImpl)
                    deleteTrackUseCase.deleteTrack(track)
                }
                val d = it
                d.remove(track)
                viewModel.trackList_sideA.value = d
            }
            binding.recyclerViewSideA.adapter = TrackRecyclerAdapter(it, this.requireContext(), onTrackDelete)
            Log.d("DEBUG", binding.recyclerViewSideA.adapter.toString())
        }
        // Список для стороны В
        binding.recyclerViewSideB.layoutManager = LinearLayoutManager(this.context)
        viewModel.trackList_sideB.observe(viewLifecycleOwner) {
            val onTrackDelete = TrackRecyclerAdapter.OnTrackDelete { track ->
                if (track.id != null) {
                    val deleteTrackUseCase = DeleteTrackUseCase(TrackRepositoryImpl)
                    deleteTrackUseCase.deleteTrack(track)
                }
                val d = it
                d.remove(track)
                viewModel.trackList_sideB.value = d
            }
            binding.recyclerViewSideB.adapter = TrackRecyclerAdapter(it, this.requireContext(), onTrackDelete)
            Log.d("DEBUG", binding.recyclerViewSideB.adapter.toString())
        }

        viewModel.cassetteObject.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.textInputLayoutCid.editText!!.setText(it.cid)
                binding.textInputLayoutTitle.editText!!.setText(it.title)
                binding.seekBarRatingAudio.progress = it.ratingAudio
                binding.seekBarRatingPhysical.progress = it.ratingPhysical
            }
        }
        viewModel.modelObject.observe(viewLifecycleOwner) {
            binding.textViewModelName.text = it.title
            binding.textViewModelTime.text = it.time.toString() + " минут"
            binding.textViewModelTapeType.text =  "Тип ленты: " + it.tapeType.toString()
        }
        viewModel.boxObject.observe(viewLifecycleOwner) {
            binding.textViewBoxTitle.text = it.name
        }

        val startForResult_selectModel = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val modelId = data!!.extras!!.getLong("modelId")
                Log.d("MODEL_ID", modelId.toString())
                viewModel.setModel(modelId)
            }
        }
        val startForResult_selectBox = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val boxId = data!!.extras!!.getLong("boxId")
                Log.d("BOX_ID", boxId.toString())
                viewModel.setBox(boxId)
            }
        }
        // Событие изменения текстового поля
        binding.textInputLayoutTitle.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("DEBUG", s.toString())
                viewModel.cassetteObject.value!!.title = s.toString()
            }
        })
        // Событие изменения текстового поля
        binding.textInputLayoutCid.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                Log.d("DEBUG", s.toString())
                viewModel.cassetteObject.value!!.cid = s.toString()
            }
        })
        // Событие нажатия на кнопку добавления песни
        binding.buttonAddTrackSideA.setOnClickListener() {
            val d = viewModel.trackList_sideA.value!!
            d.add(Track())
            viewModel.trackList_sideA.value = d
            Log.d("DEBUG", viewModel.trackList_sideA.value.toString())
        }
        // Событие нажатия на кнопку добавления песни
        binding.buttonAddTrackSideB.setOnClickListener() {
            val d = viewModel.trackList_sideB.value!!
            d.add(Track())
            viewModel.trackList_sideB.value = d
            Log.d("DEBUG", viewModel.trackList_sideB.value.toString())
        }
        // Событие нажатия на кнопку выбора модели
        binding.buttonChooseModel.setOnClickListener { view ->
            startForResult_selectModel.launch(Intent(activity, ModelListActivity::class.java)) // Открыть активити
        }
        // Событие нажатия на кнопку выбора коллекции
        binding.buttonChooseBox.setOnClickListener { view ->
            startForResult_selectBox.launch(Intent(activity, BoxListActivity::class.java)) // Открыть активити
        }
        binding.buttonSave.setOnClickListener {view ->
            // Задать значения объекту кассеты
            viewModel.cassetteObject.value!!.cid = binding.textInputLayoutCid.editText!!.text.toString()
            viewModel.cassetteObject.value!!.title = binding.textInputLayoutTitle.editText!!.text.toString()
            viewModel.cassetteObject.value!!.ratingAudio = binding.seekBarRatingAudio.progress
            viewModel.cassetteObject.value!!.ratingPhysical = binding.seekBarRatingPhysical.progress
            var valid = true
            // Пустой CID
            if (viewModel.cassetteObject.value!!.cid == Cassette.UNDEFINED_STR) {
                binding.textInputLayoutCid.error = "Введите правильное значение."
                valid = false
            }
            // Пустое название
            if (viewModel.cassetteObject.value!!.title == Cassette.UNDEFINED_STR) {
                binding.textInputLayoutTitle.error = "Введите правильное значение."
                valid = false
            }
            // Не выбрана модель кассеты
            if (viewModel.cassetteObject.value!!.modelId == Cassette.UNDEFINED_ID) {
                // Показать сообщение
                Snackbar.make(view, "Выберите модель для кассеты.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                valid = false
            }
            // Всё в порядке
            if (valid) {
                viewModel.saveCassette() // Сохранить
                parentActivity!!.close() // Закрыть
            }
        }
        // Событие нажатия на кнопку удаления
        binding.buttonDelete.setOnClickListener {view ->
            viewModel.deleteCassette() // Удалить
            parentActivity!!.close() // Закрыть
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
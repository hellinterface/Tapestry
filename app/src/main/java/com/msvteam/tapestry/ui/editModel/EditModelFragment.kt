package com.msvteam.tapestry.ui.editModel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.msvteam.tapestry.databinding.FragmentEditModelBinding
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.ui.editBox.EditBoxActivity
import com.msvteam.tapestry.ui.editBox.EditBoxViewModel

class EditModelFragment() : Fragment() {

    private var _binding: FragmentEditModelBinding? = null

    private var parentActivity : EditModelActivity? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: EditModelViewModel

    private var modelId: Long? = null

    fun setModelId(id: Long?) {
        modelId = id
    }

    fun setParentActivity(activity : EditModelActivity) {
        parentActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EditModelViewModel::class.java]
        _binding = FragmentEditModelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val minusOne: Long = -1
        if (modelId != null && modelId != minusOne) {
            viewModel.setModel(modelId!!)
        }

        viewModel.modelObject.observe(viewLifecycleOwner) {
            binding.textInputLayoutTitle.editText!!.setText(it.title)
            binding.textInputLayoutTime.editText!!.setText(it.time.toString())
            binding.seekBarTapeType.progress = it.tapeType-1
        }
        // Событие нажатия на кнопку сохранения
        binding.buttonSave.setOnClickListener {view ->
            viewModel.modelObject.value!!.title = binding.textInputLayoutTitle.editText!!.text.toString()
            viewModel.modelObject.value!!.time = (binding.textInputLayoutTime.editText!!.text.toString()).toInt()
            viewModel.modelObject.value!!.tapeType = binding.seekBarTapeType.progress+1
            var valid = true
            if (viewModel.modelObject.value!!.title == Model.UNDEFINED_STR) {
                binding.textInputLayoutTitle.error = "Введите правильное значение."
                valid = false
            }
            // Всё в порядке
            if (valid) {
                viewModel.saveModel()
                parentActivity!!.close()
            }
        }
        // Событие нажатия на кнопку удаления
        binding.buttonDelete.setOnClickListener { view ->
            viewModel.deleteModel()
            parentActivity!!.close()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
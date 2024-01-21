package com.msvteam.tapestry.ui.editBox

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.msvteam.tapestry.databinding.FragmentEditBoxBinding
import com.msvteam.tapestry.databinding.FragmentEditModelBinding
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.model.Model

class EditBoxFragment() : Fragment() {

    private var _binding: FragmentEditBoxBinding? = null
    private var parentActivity : EditBoxActivity? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: EditBoxViewModel

    private var boxId: Long? = null

    fun setBoxId(id: Long?) {
        boxId = id
    }

    fun setParentActivity(activity : EditBoxActivity) {
        parentActivity = activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[EditBoxViewModel::class.java]
        _binding = FragmentEditBoxBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val minusOne: Long = -1
        if (boxId != null && boxId != minusOne) {
            viewModel.setBox(boxId!!)
        }

        viewModel.boxObject.observe(viewLifecycleOwner) {
            binding.textInputLayoutTitle.editText!!.setText(it.name)
        }
        // Событие нажатия на кнопку сохранения
        binding.buttonSave.setOnClickListener { view ->
            viewModel.boxObject.value!!.name = binding.textInputLayoutTitle.editText!!.text.toString()
            var valid = true
            if (viewModel.boxObject.value!!.name == Box.UNDEFINED_STR) {
                binding.textInputLayoutTitle.error = "Введите правильное значение."
                valid = false
            }
            // Всё в порядке
            if (valid) {
                viewModel.saveBox() // Сохранить
                parentActivity!!.close() // Выйти
            }
        }
        // Событие нажатия на кнопку удаления
        binding.buttonDelete.setOnClickListener { view ->
            viewModel.deleteBox() // Удалить
            parentActivity!!.close() // Выйти
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
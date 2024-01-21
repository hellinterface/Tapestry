package com.msvteam.tapestry.ui.modelList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.databinding.FragmentModelListBinding
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.ui.adapters.ModelRecyclerAdapter
import com.msvteam.tapestry.ui.editBox.EditBoxActivity
import com.msvteam.tapestry.ui.editModel.EditModelActivity

class ModelListFragment : Fragment() {

    private var _binding: FragmentModelListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!
    private lateinit var viewModel: ModelListViewModel

    var recyclerViewItemClickListener = ModelRecyclerAdapter.OnClickListener { position: Int, model: Model ->
        val intent = Intent(this.context, EditModelActivity::class.java)
        intent.putExtra("modelId", model.id);
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[ModelListViewModel::class.java]
        _binding = FragmentModelListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this.context)
        viewModel.modelList.observe(viewLifecycleOwner) {
            Log.d("DEBUG", "====================================")
            Log.d("DEBUG", it.toString())
            Log.d("DEBUG", "====================================")
            val adapter = ModelRecyclerAdapter(it)
            adapter.setOnClickListener(recyclerViewItemClickListener)
            binding.recyclerViewMain.adapter = adapter
        }
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this.context, EditModelActivity::class.java)
            //intent.putExtra("cassetteId", 0);
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getRecyclerView() : RecyclerView {
        return binding.recyclerViewMain
    }
}
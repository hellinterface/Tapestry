package com.msvteam.tapestry.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.msvteam.tapestry.databinding.FragmentHomeBinding
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.ui.adapters.CassetteRecyclerAdapter
import com.msvteam.tapestry.ui.adapters.ModelRecyclerAdapter
import com.msvteam.tapestry.ui.editCassette.EditCassetteActivity
import com.msvteam.tapestry.ui.editCassette.EditCassetteFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    var recyclerViewItemClickListener = CassetteRecyclerAdapter.OnClickListener { position, cassette ->
        Log.d("DEBUG", "!!!!!!!!!!")
        val intent = Intent(this.context, EditCassetteActivity::class.java)
        intent.putExtra("cassetteId", cassette.id)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tapeList = viewModel.tapeList;
        Log.d("DEBUG", "====================================")
        Log.d("DEBUG", tapeList.isInitialized.toString())
        Log.d("DEBUG", tapeList.value.toString())
        Log.d("DEBUG", "====================================")
        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this.context)
        viewModel.tapeList.observe(viewLifecycleOwner) {
            Log.d("DEBUG", "====================================")
            Log.d("DEBUG", it.toString())
            Log.d("DEBUG", "====================================")
            val adapter = CassetteRecyclerAdapter(it)
            adapter.setOnClickListener(recyclerViewItemClickListener)
            binding.recyclerViewMain.adapter = adapter
        }
        binding.fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            val intent = Intent(this.context, EditCassetteActivity::class.java)
            //intent.putExtra("cassetteId", 0);
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
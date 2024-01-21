package com.msvteam.tapestry.ui.boxList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msvteam.tapestry.databinding.FragmentBoxListBinding
import com.msvteam.tapestry.databinding.FragmentModelListBinding
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.ui.adapters.BoxRecyclerAdapter
import com.msvteam.tapestry.ui.editBox.EditBoxActivity

class BoxListFragment : Fragment() {

    private var _binding: FragmentBoxListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!
    private lateinit var viewModel: BoxListViewModel

    private var standalone: Boolean = true

    var recyclerViewItemClickListener = BoxRecyclerAdapter.OnClickListener { position: Int, box: Box ->
        val intent = Intent(this.context, EditBoxActivity::class.java)
        intent.putExtra("boxId", box.id);
        startActivity(intent)
    }

    fun setStandalone(setTo: Boolean) {
        standalone = setTo
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[BoxListViewModel::class.java]
        _binding = FragmentBoxListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this.context)
        viewModel.boxList.observe(viewLifecycleOwner) {
            Log.d("DEBUG", "====================================")
            Log.d("DEBUG", it.toString())
            Log.d("DEBUG", "====================================")
            val list = it.toMutableList()
            if (!standalone) list.add(0, Box("Без коллекции"))
            val adapter = BoxRecyclerAdapter(list, recyclerViewItemClickListener)
            binding.recyclerViewMain.adapter = adapter
        }
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this.context, EditBoxActivity::class.java)
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
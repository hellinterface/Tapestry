package com.msvteam.tapestry.ui.modelList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.msvteam.tapestry.R
import com.msvteam.tapestry.databinding.ActivityModelListBinding
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.ui.adapters.ModelRecyclerAdapter
import com.msvteam.tapestry.ui.editBox.EditBoxFragment
import com.msvteam.tapestry.ui.editModel.EditModelFragment

class ModelListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModelListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_list)
        binding = ActivityModelListBinding.inflate(layoutInflater)

        // binding.navHostFragmentModelList.getFragment<ModelListFragment>().

        val fragment = binding.navHostFragmentModelList.getFragment<ModelListFragment>()

        var observer = LifecycleEventObserver {source: LifecycleOwner, event: Lifecycle.Event ->
            if (event == Lifecycle.Event.ON_START) {
                val frag = source as ModelListFragment
                Log.d("DEBUG", frag.getRecyclerView().adapter.toString())
                frag.recyclerViewItemClickListener = ModelRecyclerAdapter.OnClickListener { position: Int, model: Model ->
                    val intent = Intent()
                    intent.putExtra("modelId", model.id)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }

        fragment.lifecycle.addObserver(observer)
        Log.d("DEBUG", fragment.toString())
    }
}
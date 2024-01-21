package com.msvteam.tapestry.ui.boxList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.msvteam.tapestry.R
import com.msvteam.tapestry.databinding.ActivityBoxListBinding
import com.msvteam.tapestry.databinding.ActivityModelListBinding
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.ui.adapters.BoxRecyclerAdapter
import com.msvteam.tapestry.ui.adapters.ModelRecyclerAdapter
import com.msvteam.tapestry.ui.editBox.EditBoxFragment

class BoxListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoxListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_box_list)
        binding = ActivityBoxListBinding.inflate(layoutInflater)

        // binding.navHostFragmentModelList.getFragment<ModelListFragment>().

        val fragment = binding.navHostFragmentBoxList.getFragment<BoxListFragment>()

        var observer = LifecycleEventObserver {source: LifecycleOwner, event: Lifecycle.Event ->
            if (event == Lifecycle.Event.ON_START) {
                val frag = source as BoxListFragment
                Log.d("DEBUG", frag.getRecyclerView().adapter.toString())
                frag.recyclerViewItemClickListener = BoxRecyclerAdapter.OnClickListener { position: Int, box: Box ->
                    val intent = Intent()
                    intent.putExtra("boxId", box.id)
                    setResult(RESULT_OK, intent)
                    finish()
                }
                frag.setStandalone(false)
            }
        }

        fragment.lifecycle.addObserver(observer)
        Log.d("DEBUG", fragment.toString())
    }
}
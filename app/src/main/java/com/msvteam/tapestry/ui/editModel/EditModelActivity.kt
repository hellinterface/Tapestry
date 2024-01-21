package com.msvteam.tapestry.ui.editModel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.msvteam.tapestry.R
import com.msvteam.tapestry.databinding.ActivityEditModelBinding
import com.msvteam.tapestry.ui.editBox.EditBoxFragment


class EditModelActivity : FragmentActivity() {

    private var _binding: ActivityEditModelBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_model)
        _binding = ActivityEditModelBinding.inflate(layoutInflater)
        val root: View = binding.root
        val fragment = binding.fragmentContainerView.getFragment<EditModelFragment>()
        fragment.setParentActivity(this)
        fragment.setModelId(intent.getLongExtra("modelId", -1))
    }

    fun close() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}
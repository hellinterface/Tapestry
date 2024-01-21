package com.msvteam.tapestry.ui.editBox

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.msvteam.tapestry.R
import com.msvteam.tapestry.databinding.ActivityEditBoxBinding
import com.msvteam.tapestry.databinding.ActivityEditModelBinding
import com.msvteam.tapestry.ui.editCassette.EditCassetteFragment


class EditBoxActivity : FragmentActivity() {

    private var _binding: ActivityEditBoxBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_box)
        _binding = ActivityEditBoxBinding.inflate(layoutInflater)
        val root: View = binding.root
        val fragment = binding.fragmentContainerView.getFragment<EditBoxFragment>()
        fragment.setParentActivity(this)
        fragment.setBoxId(intent.getLongExtra("boxId", -1))
    }

    fun close() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}
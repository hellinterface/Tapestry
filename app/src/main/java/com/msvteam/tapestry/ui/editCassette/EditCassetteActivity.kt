package com.msvteam.tapestry.ui.editCassette

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.msvteam.tapestry.R
import com.msvteam.tapestry.databinding.ActivityEditCassetteBinding


class EditCassetteActivity : FragmentActivity() {

    private var _binding: ActivityEditCassetteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cassette)
        _binding = ActivityEditCassetteBinding.inflate(layoutInflater)
        val root: View = binding.root
        val fragment = binding.fragmentContainerView.getFragment<EditCassetteFragment>()
        fragment.setParentActivity(this)
        fragment.setCassetteId(intent.getLongExtra("cassetteId", -1))
    }

    fun close() {
        val intent = Intent()
        setResult(RESULT_OK, intent)
        finish()
    }
}
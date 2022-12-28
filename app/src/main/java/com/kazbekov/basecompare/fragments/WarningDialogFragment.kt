package com.kazbekov.basecompare.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.kazbekov.basecompare.databinding.DialogFragmentWarningBinding

class WarningDialogFragment : DialogFragment() {
    private var _binding: DialogFragmentWarningBinding? = null
    private val binding: DialogFragmentWarningBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DialogFragmentWarningBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
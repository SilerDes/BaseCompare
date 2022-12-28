package com.kazbekov.basecompare.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kazbekov.basecompare.databinding.FragmentFileProviderBinding

class FileProvider : Fragment() {
    private var _binding: FragmentFileProviderBinding? = null
    private val binding: FragmentFileProviderBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFileProviderBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
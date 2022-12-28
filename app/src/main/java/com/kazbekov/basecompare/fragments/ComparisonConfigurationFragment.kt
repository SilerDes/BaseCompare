package com.kazbekov.basecompare.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kazbekov.basecompare.databinding.FragmentComparisonConfigurationBinding

class ComparisonConfigurationFragment : Fragment() {
    private var _binding: FragmentComparisonConfigurationBinding? = null
    private val binding: FragmentComparisonConfigurationBinding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentComparisonConfigurationBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
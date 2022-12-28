package com.kazbekov.basecompare.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kazbekov.basecompare.R
import com.kazbekov.basecompare.databinding.FragmentSelectFilesBinding
import com.kazbekov.basecompare.viewModels.SelectFilesViewModel

/**
 *
 * Выбирает 2 файла формата Excel
 * Анализирует на стандартизацию
 * Если стандарт выверен, обрабатывает файлы, иначе предупреждает пользователя
 *
 **/
class SelectFilesFragment : Fragment() {
    private var _binding: FragmentSelectFilesBinding? = null
    private val binding: FragmentSelectFilesBinding
        get() = _binding!!
    private val viewModel: SelectFilesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSelectFilesBinding.bind(view)

        observeLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        binding.nextButton.setOnClickListener {
            onNextButtonClicked()
        }
    }

    private fun observeLiveData() {
        viewModel.firstFile.observe(viewLifecycleOwner) {
            checkFileStatus()
        }

        viewModel.secondFile.observe(viewLifecycleOwner) {
            checkFileStatus()
        }

        viewModel.onPassed.observe(viewLifecycleOwner) {
            binding.progressCard.visibility = View.GONE
            findNavController().navigate(
                R.id.action_selectFilesFragment_to_comparisonConfigurationFragment
            )
        }

        viewModel.notPassed.observe(viewLifecycleOwner) {
            binding.progressCard.visibility = View.GONE
            val action =
                SelectFilesFragmentDirections.actionSelectFilesFragmentToWarningDialogFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun checkFileStatus() {
        binding.nextButton.visibility =
            if (viewModel.firstFile.value != null && viewModel.secondFile.value != null) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun onNextButtonClicked() {
        //Checking files for general standardization
        viewModel.checkFileStandard()
        binding.progressCard.visibility = View.VISIBLE
    }
}
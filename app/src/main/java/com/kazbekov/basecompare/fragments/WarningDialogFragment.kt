package com.kazbekov.basecompare.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.kazbekov.basecompare.R
import com.kazbekov.basecompare.databinding.DialogFragmentWarningBinding
import com.kazbekov.basecompare.viewModels.SelectFilesRepository

class WarningDialogFragment : DialogFragment() {
    private var _binding: DialogFragmentWarningBinding? = null
    private val binding: DialogFragmentWarningBinding
        get() = _binding!!
    private val args: WarningDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = DialogFragmentWarningBinding.bind(view)

        val warningMessage = when (args.warning) {
            SelectFilesRepository.COLUMN_TYPE ->
                requireContext().getString(R.string.warning_column_type_not_equals)
            SelectFilesRepository.COLUMN_NUMBER ->
                requireContext().getString(R.string.warning_column_number_not_equals)
            else -> error("Не обработанный тип: ${args.warning}")
        }

        binding.warningMessage.text = warningMessage
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
package com.spase_y.habittracker.main.navigation_fragment.d.general_settings

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentGeneralSettingsBinding
import com.spase_y.habittracker.main.navigation_fragment.d.general_settings.privacy_policy.PrivacyPolicyFragment
import com.spase_y.habittracker.main.navigation_fragment.d.general_settings.time_intervals.TimeIntervalsFragment

class GeneralSettingsFragment : Fragment() {
    private var _binding: FragmentGeneralSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGeneralSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE


        // Обработка кнопки назад
        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.llnotification4.setOnClickListener {
            showCustomDialog()
        }

        // Открытие TimeIntervalsFragment
        binding.llnotification1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, TimeIntervalsFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.llnotification3.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, PrivacyPolicyFragment())
                .addToBackStack(null)
                .commit()
        }


        // Добавляем обработчик для вызова MyBottomSheetPickerDayFragment
        binding.llnotification2.setOnClickListener {
            val bottomSheet = MyBottomSheetPickerDayFragment().apply {
                setCallback { selectedDay ->
                    // Обновляем текст на экране с выбранным днем недели
                    binding.textView5.text = selectedDay
                }
            }
            bottomSheet.show(parentFragmentManager, "MyBottomSheetDay")
        }
    }
    private fun showCustomDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout) // Замените на свой файл разметки
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent) // Прозрачный фон для затемнения

        // Найдите элементы диалога
        val titleTextView = dialog.findViewById<TextView>(R.id.titleTextView)
        val messageTextView = dialog.findViewById<TextView>(R.id.messageTextView)
        val positiveButton = dialog.findViewById<Button>(R.id.positiveButton)
        val negativeButton = dialog.findViewById<Button>(R.id.negativeButton)

        // Установите текст
        titleTextView.text = getString(R.string.delete_all_data_title)
        messageTextView.text = getString(R.string.delete_all_data_message)

        // Обработчики кнопок
        positiveButton.setOnClickListener {
            // Действие при нажатии "ОК"
            dialog.dismiss() // Закрыть диалог
        }

        negativeButton.setOnClickListener {
            // Действие при нажатии "Отмена"
            dialog.dismiss() // Закрыть диалог
        }

        // Покажите диалог
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.BottomSheetDayLayoutBinding

class MyBottomSheetDayFragment : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDayLayoutBinding? = null
    private val binding get() = _binding!!

    // Храним выбранные дни недели
    private val selectedDays = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDayLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настройка кнопок для дней недели
        setupDayButton(binding.buttonSunday, "ВС")
        setupDayButton(binding.buttonMonday, "ПН")
        setupDayButton(binding.buttonTuesday, "ВТ")
        setupDayButton(binding.buttonWednesday, "СР")
        setupDayButton(binding.buttonThursday, "ЧТ")
        setupDayButton(binding.buttonFriday, "ПТ")
        setupDayButton(binding.buttonSaturday, "СБ")

        // Установка действий для кнопок сохранения/отмены
        binding.btnSave.setOnClickListener {
            // Логика для сохранения выбранных дней
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            // Логика для отмены
            dismiss()
        }
    }

    private fun setupDayButton(button: AppCompatButton, day: String) {
        button.setOnClickListener {
            if (selectedDays.contains(day)) {
                // Если день уже выбран — убираем выбор
                selectedDays.remove(day)
                button.setBackgroundResource(R.drawable.button_shape_graffit_light_r12)
            } else {
                // Если день не выбран — добавляем выбор
                selectedDays.add(day)
                button.setBackgroundResource(R.drawable.button_shape_blue_light_r12)
            }
            updateSelectedDaysText()
        }
    }

    private fun updateSelectedDaysText() {
        // Обновляем текст в TextView
        binding.textViewMessage2.text = if (selectedDays.isEmpty()) {
            "Выберите дни"
        } else {
            selectedDays.joinToString(", ")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

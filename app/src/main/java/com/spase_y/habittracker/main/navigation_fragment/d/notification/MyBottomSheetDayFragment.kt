package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.Days
import com.spase_y.habittracker.databinding.BottomSheetDayLayoutBinding

class MyBottomSheetDayFragment(private var selectedDays: MutableList<Days>, val onSave: (List<Days>) -> Unit) : BottomSheetDialogFragment() {

    private var _binding: BottomSheetDayLayoutBinding? = null
    private val binding get() = _binding!!

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
        setupDayButton(binding.buttonSunday, Days.SUNDAY)
        setupDayButton(binding.buttonMonday, Days.MONDAY)
        setupDayButton(binding.buttonTuesday, Days.TUESDAY)
        setupDayButton(binding.buttonWednesday, Days.WEDNESDAY)
        setupDayButton(binding.buttonThursday, Days.THURSDAY)
        setupDayButton(binding.buttonFriday, Days.FRIDAY)
        setupDayButton(binding.buttonSaturday, Days.SATURDAY)

        // Установка действий для кнопок сохранения/отмены
        binding.btnSave.setOnClickListener {
            // Логика для сохранения выбранных дней
            onSave.invoke(selectedDays)
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            // Логика для отмены
            dismiss()
        }

        if (selectedDays.size == 1) {
            if (selectedDays[0] == Days.EVERYDAY) {
                selectedDays = mutableListOf(
                    Days.MONDAY,
                    Days.TUESDAY,
                    Days.WEDNESDAY,
                    Days.THURSDAY,
                    Days.FRIDAY,
                    Days.SUNDAY,
                    Days.SATURDAY,
                )
            }
        }
        updateSelectedDaysText()
        selectedDays.forEach {
            when (it) {
                Days.MONDAY -> { binding.buttonMonday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.TUESDAY -> { binding.buttonTuesday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.WEDNESDAY -> { binding.buttonWednesday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.THURSDAY -> { binding.buttonThursday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.FRIDAY -> { binding.buttonFriday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.SUNDAY -> { binding.buttonSunday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.SATURDAY -> { binding.buttonSaturday.setBackgroundResource(R.drawable.button_shape_blue_light_r12) }
                Days.EVERYDAY -> {}
            }

        }
    }

    private fun setupDayButton(button: AppCompatButton, day: Days) {
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
            getString(R.string.select_days)
        } else {
            selectedDays.joinToString(", ")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

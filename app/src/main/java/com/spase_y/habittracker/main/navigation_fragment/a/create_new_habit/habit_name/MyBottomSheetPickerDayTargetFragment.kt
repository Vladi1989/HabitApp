package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R
import com.spase_y.habittracker.main.navigation_fragment.d.general_settings.MyBottomSheetPickerDayFragment

class MyBottomSheetPickerDayTargetFragment:BottomSheetDialogFragment() {
    private var callback: ((String) -> Unit)? = null
    private val dayTarget = arrayOf(
        "Выкл", "Длительность", "Повторение"
    )
    fun setCallback(callback: (String) -> Unit) {
        this.callback = callback
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_target, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberPickerDays: com.shawnlin.numberpicker.NumberPicker = view.findViewById(R.id.number_picker_days)
        val buttonPositive: Button = view.findViewById(R.id.button_positive)
        val buttonNegative: Button = view.findViewById(R.id.button_negative)

        // Устанавливаем диапазон значений для NumberPicker
        numberPickerDays.minValue = 0
        numberPickerDays.maxValue = dayTarget.size - 1
        numberPickerDays.displayedValues = dayTarget

        // Кнопка для выбора
        buttonPositive.setOnClickListener {
            val selectedDay = dayTarget[numberPickerDays.value] // Получаем выбранный день
            callback?.invoke(selectedDay) // Передаем выбранный день через callback
            dismiss() // Закрываем BottomSheet
        }

        // Кнопка для отмены
        buttonNegative.setOnClickListener {
            dismiss() // Закрываем BottomSheet
        }
    }

    companion object {
        fun newInstance(): MyBottomSheetPickerDayFragment {
            return MyBottomSheetPickerDayFragment()
        }
    }
}

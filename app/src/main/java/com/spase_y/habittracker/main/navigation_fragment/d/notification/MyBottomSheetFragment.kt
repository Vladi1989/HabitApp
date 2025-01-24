package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.shawnlin.numberpicker.NumberPicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R

class MyBottomSheetTimeFragment : BottomSheetDialogFragment() {

    private var callback: ((String) -> Unit)? = null
    private var minHour: Int = 0
    private var maxHour: Int = 23

    fun setCallback(callback: (String) -> Unit) {
        this.callback = callback
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            minHour = it.getInt("MIN_HOUR", 0)
            maxHour = it.getInt("MAX_HOUR", 23)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberPickerHours: NumberPicker = view.findViewById(R.id.number_picker_hours)
        val numberPickerMinutes: NumberPicker = view.findViewById(R.id.number_picker_minute)
        val buttonPositive: Button = view.findViewById(R.id.button_positive)
        val buttonNegative: Button = view.findViewById(R.id.button_negative)

        // Устанавливаем диапазоны значений для NumberPicker
        numberPickerHours.minValue = minHour
        numberPickerHours.maxValue = maxHour
        numberPickerMinutes.minValue = 0
        numberPickerMinutes.maxValue = 59

        buttonPositive.setOnClickListener {
            val selectedTime = String.format(
                "%02d:%02d", numberPickerHours.value, numberPickerMinutes.value
            )
            callback?.invoke(selectedTime)
            dismiss()
        }

        buttonNegative.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(minHour: Int, maxHour: Int): MyBottomSheetTimeFragment {
            val fragment = MyBottomSheetTimeFragment()
            val args = Bundle()
            args.putInt("MIN_HOUR", minHour)
            args.putInt("MAX_HOUR", maxHour)
            fragment.arguments = args
            return fragment
        }
    }
}
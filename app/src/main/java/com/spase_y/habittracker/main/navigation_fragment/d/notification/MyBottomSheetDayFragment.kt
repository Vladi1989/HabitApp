package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R

class MyBottomSheetDayFragment: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.bottom_sheet_day_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonPositive: Button = view.findViewById(R.id.btnSave)
        val buttonNegative: Button = view.findViewById(R.id.btnCancel)

        // Устанавливаем действия на кнопки
        buttonPositive.setOnClickListener {
            // Действие для кнопки "Принять"
            dismiss() // Закрыть Bottom Sheet
        }

        buttonNegative.setOnClickListener {
            // Действие для кнопки "Отменить"
            dismiss() // Закрыть Bottom Sheet
        }
    }
}
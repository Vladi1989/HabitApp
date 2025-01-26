package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.spase_y.habittracker.R

class ColorPickerBottomSheetFragment(
    private val onColorSelected: (Int) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_color_picker, container, false)

        val colorPalette = view.findViewById<GridLayout>(R.id.colorPalette)
        val colors = listOf(
            android.graphics.Color.RED,
            android.graphics.Color.BLUE,
            android.graphics.Color.GREEN,
            android.graphics.Color.YELLOW,
            android.graphics.Color.CYAN,
            android.graphics.Color.MAGENTA,
            android.graphics.Color.LTGRAY,
            android.graphics.Color.DKGRAY,
            android.graphics.Color.BLACK,           // Новый цвет: черный
            android.graphics.Color.parseColor("#FFA500"), // Новый цвет: оранжевый
            android.graphics.Color.parseColor("#800080")  // Новый цвет: фиолетовый
        )

        // Заполняем палитру цветов
        colors.forEach { color ->
            val colorView = View(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 200
                    height = 200
                    setMargins(16, 16, 16, 16)
                }
                background = ContextCompat.getDrawable(context, R.drawable.circle_square_color)
                background.setTint(color)

                setOnClickListener {
                    onColorSelected(color)
                    dismiss()
                }
            }
            colorPalette.addView(colorView)
        }

        view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
            dismiss()
        }

        view.findViewById<Button>(R.id.btnSave).setOnClickListener {
            dismiss()
        }

        return view
    }
}
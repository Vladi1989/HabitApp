package com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.spase_y.habittracker.R
import java.util.Calendar

class CalendarFragment : Fragment() {
    private lateinit var tvMonthYear: TextView
    private lateinit var gridDays: GridLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        tvMonthYear = view.findViewById(R.id.tvMonthYear)
        gridDays = view.findViewById(R.id.gridDays)
        setupCalendar(2025, 1) // Передаем январь 2025 года
        return view
    }

    private fun setupCalendar(year: Int, month: Int) {
        // Устанавливаем текущий месяц и год в TextView
        tvMonthYear.text = String.format("%02d,%d", month, year)

        // Получаем количество дней в месяце
        val calendar = Calendar.getInstance().apply {
            set(year, month - 1, 1)
        }
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        // Определяем, на какой день недели выпадает первое число
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1 = Sunday

        // Очищаем предыдущую сетку
        gridDays.removeAllViews()

        // Добавляем пустые ячейки до первого дня
        val emptyCells = (firstDayOfWeek - 1) % 7 // Индексируем с ВС
        for (i in 0 until emptyCells) {
            addDayCell("")
        }

        // Добавляем числа месяца
        for (day in 1..daysInMonth) {
            addDayCell(day.toString())
        }
    }

    private fun addDayCell(day: String) {
        val textView = TextView(requireContext()).apply {
            text = day
            gravity = Gravity.CENTER
            textSize = 16f
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(4, 4, 4, 4)
            }
        }
        gridDays.addView(textView)
    }
}
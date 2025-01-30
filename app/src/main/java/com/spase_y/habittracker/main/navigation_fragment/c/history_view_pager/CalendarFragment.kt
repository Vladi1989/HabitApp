package com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentCalendarBinding
import com.spase_y.habittracker.main.MainAppFragment.Companion.openFragmentToday
import kotlinx.coroutines.flow.combine
import java.util.Calendar

class CalendarFragment : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        setupCalendar(2025, 1) // Январь 2025 года
        return binding.root
    }

    private fun setupCalendar(year: Int, month: Int) {
        // Устанавливаем заголовок месяца и года
        binding.tvMonthYear.text = String.format("%02d, %d", month, year)

        // Получаем количество дней в месяце
        val calendar = Calendar.getInstance().apply {
            set(year, month - 1, 1)
        }
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 1 = Sunday

        // Очищаем сетку перед заполнением
        binding.gridDays.removeAllViews()

        Handler().postDelayed({
            // Добавляем пустые ячейки до первого дня
            val emptyCells = (firstDayOfWeek - 1) % 7
            for (i in 0 until emptyCells) {
                addDayCell("")
            }

            // Добавляем числа месяца
            for (day in 1..daysInMonth) {
                addDayCell(day.toString())
            }
        }, 10)
    }

    private fun addDayCell(day: String) {
        val cellSize = binding.gridDays.width / 7
        val textView = TextView(requireContext()).apply {
            text = day
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.WHITE) // Здесь меняем цвет текста (можно использовать ресурс)
            layoutParams = GridLayout.LayoutParams().apply {
                width = 0
                height = cellSize - 8
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(4, 4, 4, 4)
            }
            if (day != "") {
                setOnClickListener {
                    openFragmentToday(day.toInt())
                }
            }
        }
        binding.gridDays.addView(textView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
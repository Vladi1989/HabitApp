package com.spase_y.habittracker.main.navigation_fragment.a

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.R
import com.spase_y.habittracker.adapters.HabitsAdapter
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.databinding.FragmentMainATodayBinding
import com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.CreateNewHabitFragment
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoField
import java.time.temporal.TemporalAdjusters
import java.util.Date

class MainFragmentAToday : Fragment() {

    private var _binding: FragmentMainATodayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainATodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var localDate = LocalDate.now()
        val selectedDayFromHistory = arguments?.getInt("Day")
        if (selectedDayFromHistory != null) {
            localDate = LocalDate.now().withDayOfMonth(selectedDayFromHistory)
            onDateSelect(localDate)
        }

        binding.btnGoToCreateNewHabitFragment.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, CreateNewHabitFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.btnGoToCreateNewHabitFragment2.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, CreateNewHabitFragment())
                .addToBackStack(null)
                .commit()
        }

        // Находим все кнопки по их id
        val button1 = binding.button1
        val button2 = binding.button2
        val button3 = binding.button3
        val button4 = binding.button4

        // Список всех кнопок
        val allButtons = listOf(button1, button2, button3, button4)

        // Устанавливаем обработчик кликов для каждой кнопки

        button1.setOnClickListener {
            setActiveButton(button1, allButtons)
        }

        button2.setOnClickListener {
            setActiveButton(button2, allButtons)
        }

        button3.setOnClickListener {
            setActiveButton(button3, allButtons)
        }

        button4.setOnClickListener {
            setActiveButton(button4, allButtons)
        }

        // Определение первого дня текущей недели (понедельника)
        val startDate = LocalDate.now().withDayOfYear(1)
        val weeks = generateWeeks(startDate, 52) // Генерация недель на год

        // Настройка адаптера для ViewPager2
        val adapter = WeekPagerAdapter(weeks, localDate) { date ->
            // Обработка нажатий на конкретный день
            onDateSelect(date)
        }

        // Привязка адаптера к ViewPager
        binding.viewPager.adapter = adapter

        // Установка текущей недели (например, середина года)
        val currentWeek = LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR) - 1
        binding.viewPager.setCurrentItem(currentWeek, false)



        binding.rvHabits.layoutManager = LinearLayoutManager(requireContext())
        habitsAdapter.listHabits = habitsManager.getAllHabits()
        binding.rvHabits.adapter = habitsAdapter
    }

    private fun onDateSelect(date: LocalDate) {
        val localDate = LocalDate.now()
        binding.textView.text = date.toString()
        Toast.makeText(requireContext(), "Вы выбрали: $date", Toast.LENGTH_SHORT).show()

    }

    val habitsAdapter = HabitsAdapter()

    val habitsManager by lazy {
        HabitsManger(getSharedPreferences())
    }

    private fun setActiveButton(activeButton: Button, allButtons: List<Button>) {
        // Перебираем все кнопки
        for (button in allButtons) {
            if (button == activeButton) {
                // Устанавливаем цвет activeButton на primary
                button.backgroundTintList = ColorStateList.valueOf(
                    resources.getColor(R.color.primary, null)
                )

                // Показываем тост
                Toast.makeText(requireContext(), "Кнопка '${button.text}' активна!", Toast.LENGTH_SHORT).show()
            } else {
                // Остальным кнопкам ставим цвет card_view
                button.backgroundTintList = ColorStateList.valueOf(
                    resources.getColor(R.color.card_view, null)
                )
            }
        }
    }

    // Функция генерации списка недель
    private fun generateWeeks(startDate: LocalDate, numWeeks: Int): List<List<LocalDate>> {
        val weeks = mutableListOf<List<LocalDate>>()
        var currentStart = startDate
        for (i in 0 until numWeeks) {
            val week = (0..6).map { currentStart.plusDays(it.toLong()) }
            weeks.add(week)
            currentStart = currentStart.plusWeeks(1)
        }
        return weeks
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
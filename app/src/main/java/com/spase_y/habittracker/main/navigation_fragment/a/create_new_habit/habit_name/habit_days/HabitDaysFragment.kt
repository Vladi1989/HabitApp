package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name.habit_days

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.transition.Visibility
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentHabitDaysBinding


class HabitDaysFragment : Fragment() {
    private var _binding: FragmentHabitDaysBinding? = null
    private val binding get() = _binding!!
    private var selectedDayIndex: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitDaysBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        setupDayButton(binding.button1, 1)
        setupDayButton(binding.button2, 2)
        setupDayButton(binding.button3, 3)
        setupDayButton(binding.button4, 4)
        setupDayButton(binding.button5, 5)
        setupDayButton(binding.button6, 6)

        binding.numberPickerMonth.setOnValueChangedListener { _, _, newVal ->
            updateTextViewWithDays(newVal)
        }

        // Устанавливаем начальное значение для TextView
        updateTextViewWithDays(binding.numberPickerMonth.value)

        // Обработка NumberPicker для "дней в году"
        binding.numberPickerDays.setOnValueChangedListener { _, _, newVal ->
            updateYearDaysTextView(newVal)
        }

        // Устанавливаем начальное значение для TextView
        updateYearDaysTextView(binding.numberPickerDays.value)


        binding.llWeekClose.setOnClickListener {
            binding.llmYearClose.visibility  = View.VISIBLE
            binding.llmMonthClose.visibility  = View.VISIBLE
            binding.llWeekClose.visibility  = View.GONE
            binding.llMounthClose.visibility = View.VISIBLE
            binding.llMounthOpen.visibility = View.GONE
            binding.llYearOpen.visibility = View.GONE
        }
        binding.llmMonthClose.setOnClickListener {
            binding.llmYearClose.visibility  = View.VISIBLE
            binding.llWeekClose.visibility  = View.VISIBLE
            binding.llmMonthClose.visibility  = View.GONE
            binding.llMounthClose.visibility = View.GONE
            binding.llMounthOpen.visibility = View.VISIBLE
            binding.llYearOpen.visibility = View.GONE
        }
        binding.llmYearClose.setOnClickListener {
            binding.llmMonthClose.visibility  = View.VISIBLE
            binding.llWeekClose.visibility  = View.VISIBLE
            binding.llmYearClose.visibility  = View.GONE
            binding.llMounthClose.visibility = View.GONE
            binding.llMounthOpen.visibility = View.GONE
            binding.llYearOpen.visibility = View.VISIBLE
        }
    }
    private fun setupDayButton(button: AppCompatButton, dayIndex: Int) {
        button.setOnClickListener {
            // Сбрасываем ранее выбранную кнопку
            if (selectedDayIndex != null && selectedDayIndex != dayIndex) {
                resetButtonBackground(selectedDayIndex!!)
            }

            // Обновляем выбранную кнопку
            if (selectedDayIndex == dayIndex) {
                // Если нажата уже активная кнопка — снимаем выбор
                selectedDayIndex = null
                button.setBackgroundResource(R.drawable.button_shape_card_view_r12)
                updateSelectedDaysText()
            } else {
                // Если нажата новая кнопка — выбираем её
                selectedDayIndex = dayIndex
                button.setBackgroundResource(R.drawable.button_shape_blue_light_r12)
                updateSelectedDaysText()
            }
        }
    }

    private fun resetButtonBackground(dayIndex: Int) {
        when (dayIndex) {
            1 -> binding.button1.setBackgroundResource(R.drawable.button_shape_card_view_r12)
            2 -> binding.button2.setBackgroundResource(R.drawable.button_shape_card_view_r12)
            3 -> binding.button3.setBackgroundResource(R.drawable.button_shape_card_view_r12)
            4 -> binding.button4.setBackgroundResource(R.drawable.button_shape_card_view_r12)
            5 -> binding.button5.setBackgroundResource(R.drawable.button_shape_card_view_r12)
            6 -> binding.button6.setBackgroundResource(R.drawable.button_shape_card_view_r12)
        }
    }

    private fun updateSelectedDaysText() {
        binding.textView6.text = if (selectedDayIndex == null) {
            "Выберите дни"
        } else {
            "$selectedDayIndex ${getDaySuffix(selectedDayIndex!!)} в неделю"
        }
    }

    private fun getDaySuffix(day: Int): String {
        return when (day) {
            1 -> "день"
            2, 3, 4 -> "дня"
            else -> "дней"
        }
    }
    private fun updateTextViewWithDays(days: Int) {
        val text = "$days ${getDaySuffix(days)} в месяц"
        binding.textView14.text = text
    }
    private fun updateYearDaysTextView(days: Int) {
        val text = "$days ${getDaySuffixForYear(days)} в году"
        binding.textView22.text = text
    }

    private fun getDaySuffixForYear(day: Int): String {
        return when (day) {
            1 -> "день"
            2, 3, 4 -> "дня"
            else -> "дней"
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
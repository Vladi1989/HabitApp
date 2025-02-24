package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.Days
import com.spase_y.habittracker.data.HabitSettings
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.data.HarmfulHabit
import com.spase_y.habittracker.data.OneTimeHabit
import com.spase_y.habittracker.data.RegularHabit
import com.spase_y.habittracker.databinding.BottomSheetIconsBinding
import com.spase_y.habittracker.databinding.FragmentHabitNameBinding
import com.spase_y.habittracker.databinding.FragmentMainCHistoryBinding
import com.spase_y.habittracker.main.navigation_fragment.a.MainFragmentAToday
import com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name.habit_days.HabitDaysFragment
import com.spase_y.habittracker.main.navigation_fragment.d.general_settings.MyBottomSheetPickerDayFragment
import com.spase_y.habittracker.main.navigation_fragment.d.notification.MyBottomSheetTimeFragment

class HabitNameFragment : Fragment() {
    private var _binding: FragmentHabitNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHabitNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.llSettings.setOnClickListener {
            // Проверяем текущую видимость элементов
            if (binding.textView25.visibility == View.VISIBLE) {
                // Если элементы уже видимы, скрываем их
                binding.textView25.visibility = View.GONE
                binding.cv2.visibility = View.GONE
                binding.appCompatButton9.visibility = View.GONE
                binding.appCompatButton10.visibility = View.GONE
                binding.textView27.visibility = View.GONE
            } else {
                // Если элементы скрыты, показываем их
                binding.textView25.visibility = View.VISIBLE
                binding.cv2.visibility = View.VISIBLE
                binding.appCompatButton9.visibility = View.VISIBLE
                binding.appCompatButton10.visibility = View.VISIBLE
                binding.textView27.visibility = View.VISIBLE
            }
        }

        val button1 = binding.appCompatButton
        val button2 = binding.appCompatButton2
        val button3 = binding.appCompatButton3
        val button4 = binding.appCompatButton4

        val allButtons = listOf(button1, button2, button3, button4)

        val buttonOff = binding.appCompatButton9
        val buttonData = binding.appCompatButton10

        val finishButtons = listOf(buttonOff,buttonData)

        binding.swTotal.isChecked = false // Отключаем switch при старте
        binding.showBottomSheetButton.visibility = View.GONE // Скрываем кнопку при старте

        binding.swTotal.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.showBottomSheetButton.visibility = View.VISIBLE
            } else {
                binding.showBottomSheetButton.visibility = View.GONE
            }
        }

        buttonOff.setOnClickListener {
            setActiveButton(buttonOff,finishButtons)
            binding.llDaysInWeek.visibility = View.GONE
        }
        buttonData.setOnClickListener {
            setActiveButton(buttonData,finishButtons)
            binding.llDaysInWeek.visibility = View.VISIBLE
        }

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

        val habitId = requireArguments().getInt("HabitType")

        binding.llnotification1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp,HabitDaysFragment())
                .addToBackStack(null)
                .commit()
        }
        binding.llnotification4.setOnClickListener {
            val bottomSheet = MyBottomSheetPickerDayTargetFragment().apply {
                setCallback { selectedDay ->
                    // Обновляем текст на экране с выбранным днем недели
                    binding.textView10.text = selectedDay
                }
            }
            bottomSheet.show(parentFragmentManager, "MyBottomSheetDay")
        }

        // Кнопка назад
        binding.ivArrowCrossBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.imageView10.setOnClickListener {
            showIconPicker()
        }
        binding.textView3.setOnClickListener {
            showIconPicker()
        }

        // Открытие палитры цветов по нажатию на круглый элемент
        binding.textView5.setOnClickListener {
            val colorPickerBottomSheet = ColorPickerBottomSheetFragment { selectedColor ->
                currentSelectedColor = selectedColor

                // Устанавливаем круглый фон и меняем его цвет
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_color)
                drawable?.setTint(selectedColor)
                binding.colorPickerCircle.background = drawable

                // Меняем цвет иконки
                binding.imageView10.setColorFilter(selectedColor)
            }
            colorPickerBottomSheet.show(parentFragmentManager, "ColorPicker")
        }


        binding.btnGoToStartD.setOnClickListener {

            val manager = HabitsManger(getSharedPreferences())
            val habitName = binding.etHabitName.text.toString()
            val habitIcon = currentSelectedIcon
            val habitDays = listOf(Days.EVERYDAY)
            if (habitId == 1) {
                manager.addHabit(
                    RegularHabit(
                        habitName,
                        habitIcon,
                        currentSelectedColor,
                        System.currentTimeMillis(),
                        habitDays
                    )
                )
            } else if (habitId == 2) {
                manager.addHabit(
                    HarmfulHabit(
                        habitName,
                        habitIcon,
                        currentSelectedColor,
                        System.currentTimeMillis(),
                    )
                )
            } else if (habitId == 3) {
                manager.addHabit(
                    OneTimeHabit(
                        habitName,
                        habitIcon,
                        currentSelectedColor,
                        System.currentTimeMillis(),
                        System.currentTimeMillis(),
                    )
                )
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp,MainFragmentAToday())
                .addToBackStack(null)
                .commit()
        }

        currentSelectedColor = _currentSelectedColor
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.circle_color)
        drawable?.setTint(currentSelectedColor)
        binding.colorPickerCircle.background = drawable
        binding.imageView10.setColorFilter(currentSelectedColor)

        binding.showBottomSheetButton.setOnClickListener {
            val bottomSheet = MyBottomSheetTimeFragment.newInstance(0, 23)
            bottomSheet.setCallback { selectedTime ->
                binding.showBottomSheetButton.text = selectedTime  // Отобразим выбранное время в TextView
            }
            bottomSheet.show(parentFragmentManager, "TimePicker")
        }

    }

    val _currentSelectedColor by lazy {
        ContextCompat.getColor(requireContext(), R.color.primary)
    }
    var currentSelectedColor = android.graphics.Color.BLACK

    var currentSelectedIcon = R.drawable.habit_icon_4




    private fun showIconPicker() {
        val icons = mutableListOf<Int>() // Создаем изменяемый список иконок

        // Заполняем список иконок с помощью цикла for
        for (i in 1..80) {
            val iconId = resources.getIdentifier("habit_icon_$i", "drawable", requireContext().packageName)
            if (iconId != 0) { // Проверяем, что идентификатор найден
                icons.add(iconId)
            }
        }

        // Создание BottomSheet с использованием ViewBinding
        val bottomSheetBinding = BottomSheetIconsBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        // Настройка RecyclerView в BottomSheet
        bottomSheetBinding.iconRecyclerView.layoutManager = GridLayoutManager(requireContext(), 4) // 4 иконки в строке
        bottomSheetBinding.iconRecyclerView.adapter = IconAdapter(icons) { selectedIcon ->
            // Замена иконки в imageView10
            binding.imageView10.setImageResource(selectedIcon)
            bottomSheetDialog.dismiss()

            currentSelectedIcon = selectedIcon
        }

        // Показать BottomSheet
        bottomSheetDialog.show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
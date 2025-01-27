package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val habitId = requireArguments().getInt("HabitType")

        binding.llnotification1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp,HabitDaysFragment())
                .addToBackStack(null)
                .commit()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
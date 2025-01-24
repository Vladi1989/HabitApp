package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.Days
import com.spase_y.habittracker.data.HabitSettings
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.data.RegularHabit
import com.spase_y.habittracker.databinding.BottomSheetIconsBinding
import com.spase_y.habittracker.databinding.FragmentHabitNameBinding
import com.spase_y.habittracker.databinding.FragmentMainCHistoryBinding

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
            manager.addHabit(
                RegularHabit(
                    binding.etHabitName.text.toString(),
                    R.drawable.habit_icon_100,
                    currentSelectedColor,
                    System.currentTimeMillis(),
                    listOf(Days.EVERYDAY)
                )
            )
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    var currentSelectedColor = android.graphics.Color.WHITE

    private fun showIconPicker() {
        // Список иконок
        val icons = listOf(
            R.drawable.habit_icon_1,
            R.drawable.habit_icon_2,
            R.drawable.habit_icon_3,
            R.drawable.habit_icon_4,
            R.drawable.habit_icon_5,
            R.drawable.habit_icon_6,
            R.drawable.habit_icon_7,
            R.drawable.habit_icon_8,
            R.drawable.habit_icon_9,
            R.drawable.habit_icon_10,
            R.drawable.habit_icon_11,
            R.drawable.habit_icon_12,
            R.drawable.habit_icon_13,
            R.drawable.habit_icon_14,
            R.drawable.habit_icon_15,
            R.drawable.habit_icon_16,
            R.drawable.habit_icon_17,
            R.drawable.habit_icon_18,
            R.drawable.habit_icon_19,
            R.drawable.habit_icon_20,
            R.drawable.habit_icon_21,
            R.drawable.habit_icon_22,
            R.drawable.habit_icon_23,
            R.drawable.habit_icon_24,

        )

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
        }

        // Показать BottomSheet
        bottomSheetDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
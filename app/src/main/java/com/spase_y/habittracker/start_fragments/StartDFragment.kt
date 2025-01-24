package com.spase_y.habittracker.start_fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.R
import com.spase_y.habittracker.data.Days
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.data.RegularHabit
import com.spase_y.habittracker.data.RepeatHabitCount
import com.spase_y.habittracker.databinding.FragmentStartDBinding

class StartDFragment : Fragment() {
    private var _binding: FragmentStartDBinding? = null
    private val binding get() = _binding!!

    private var selectedCardIndex = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val grayColor = ContextCompat.getColor(requireContext(), R.color.card_view)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)
        val blueColor = ContextCompat.getColor(requireContext(), R.color.primary)

        binding.cardViewEditTextD.setCardBackgroundColor(grayColor)

        // Устанавливаем слушатель изменения фокуса для EditText
        binding.etEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.cardViewEditTextD.setCardBackgroundColor(blackColor)
                binding.ivCheckMarkEditText.visibility = View.VISIBLE

                // Сбрасываем фон всех карточек при фокусе на EditText
                resetAllCardViewsToDefault()
                selectedCardIndex = -1 // Сбрасываем выбор карточек
            } else {
                binding.cardViewEditTextD.setCardBackgroundColor(blackColor)
            }
        }

        // Устанавливаем начальный черный фон для всех CardView
        setInitialCardViewBackgrounds()

        // Устанавливаем обработчики кликов для CardView
        setCardViewClickListeners()

        // Обработка кнопки назад
        binding.ivArrowBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // Переход на следующий фрагмент
        binding.btnGoToStartE.setOnClickListener {
            if (selectedCardIndex == -1) {
                Toast.makeText(
                    requireContext(),
                    "Вы не выбрали ни одной привычки",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addHabit()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, StartEFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        binding.btnMissGoToStartE.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, StartEFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun addHabit() {
        val list = listOf(
            R.drawable.habit_icon_3,
            R.drawable.habit_icon_50,
            R.drawable.habit_icon_59,
            R.drawable.habit_icon_11,
            R.drawable.habit_icon_118,
        )
        val list2 = listOf(
            binding.tvText1.text.toString(),
            binding.tvText2.text.toString(),
            binding.tvText3.text.toString(),
            binding.tvText4.text.toString(),
            binding.tvText5.text.toString(),
        )

        val icon = list[selectedCardIndex]
        val text = list2[selectedCardIndex]

        val manager = HabitsManger(getSharedPreferences())
        manager.addHabit(
            RegularHabit(
                text,
                icon,
                Color.WHITE,
                System.currentTimeMillis(),
                listOf(Days.EVERYDAY),
            )
        )
    }

    private fun setInitialCardViewBackgrounds() {
        val blackColor = resources.getColor(R.color.card_view, null)

        // Устанавливаем начальный черный фон для каждой карточки
        binding.cardViewD1.setCardBackgroundColor(blackColor)
        binding.cardViewD2.setCardBackgroundColor(blackColor)
        binding.cardViewD3.setCardBackgroundColor(blackColor)
        binding.cardViewD4.setCardBackgroundColor(blackColor)
        binding.cardViewD5.setCardBackgroundColor(blackColor)
    }

    private fun setCardViewClickListeners() {
        val blackColor = resources.getColor(R.color.card_view, null)
        val blueColor = resources.getColor(R.color.primary, null)

        // Список всех CardView
        val cardViews = listOf(
            binding.cardViewD1,
            binding.cardViewD2,
            binding.cardViewD3,
            binding.cardViewD4,
            binding.cardViewD5
        )

        // Устанавливаем обработчики кликов для всех карточек
        cardViews.forEachIndexed { index, cardView ->
            cardView.tag = index.toString()
            cardView.setOnClickListener {
                // Сбрасываем фон всех карточек
                resetAllCardViewsToDefault()
                // Активируем текущую карточку
                (it as CardView).setCardBackgroundColor(blueColor)
                selectedCardIndex = it.tag.toString().toInt()
            }
        }
    }

    private fun resetAllCardViewsToDefault() {
        val blackColor = resources.getColor(R.color.card_view, null)

        // Список всех CardView
        val cardViews = listOf(
            binding.cardViewD1,
            binding.cardViewD2,
            binding.cardViewD3,
            binding.cardViewD4,
            binding.cardViewD5
        )

        // Сбрасываем фон всех карточек
        cardViews.forEach { cardView ->
            cardView.setCardBackgroundColor(blackColor)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

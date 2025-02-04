package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentCreateNewHabitBinding
import com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name.HabitNameFragment
import com.spase_y.habittracker.main.navigation_fragment.a.recommend_habit.RecommendHabitsFragment

class CreateNewHabitFragment : Fragment() {
    private var _binding: FragmentCreateNewHabitBinding? = null
    private val binding get() = _binding!!

    // Изначально первый выделен
    private var isSelectedCard1 = true
    private var isSelectedCard2 = false
    private var isSelectedCard3 = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clList1.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp,RecommendHabitsFragment())
                .addToBackStack(null)
                .commit()
        }

        // Устанавливаем начальные цвета
        updateCardViewColors()

        // Обработчики кликов для CardView
        binding.cardView1.setOnClickListener {
            habitTypeId = 1
            isSelectedCard1 = true
            isSelectedCard2 = false
            isSelectedCard3 = false
            updateCardViewColors()
            binding.linearLayout4.visibility = View.VISIBLE
            binding.linearLayout44.visibility = View.INVISIBLE
            binding.linearLayout444.visibility = View.INVISIBLE
        }

        binding.cardView2.setOnClickListener {
            habitTypeId = 2
            isSelectedCard1 = false
            isSelectedCard2 = true
            isSelectedCard3 = false
            updateCardViewColors()
            binding.linearLayout4.visibility = View.INVISIBLE
            binding.linearLayout44.visibility = View.VISIBLE
            binding.linearLayout444.visibility = View.INVISIBLE
        }

        binding.cardView3.setOnClickListener {
            habitTypeId = 3
            isSelectedCard1 = false
            isSelectedCard2 = false
            isSelectedCard3 = true
            updateCardViewColors()
            binding.linearLayout4.visibility = View.INVISIBLE
            binding.linearLayout44.visibility = View.INVISIBLE
            binding.linearLayout444.visibility = View.VISIBLE
        }

        // Пример обработчика для перехода
        binding.btnGoToHabitName.setOnClickListener {
            val fragment = HabitNameFragment()
            fragment.arguments = bundleOf(
                Pair("HabitType", habitTypeId)
            )
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    var habitTypeId = 1

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Метод для обновления цветов CardView
    private fun updateCardViewColors() {
        binding.cardView1.setCardBackgroundColor(
            if (isSelectedCard1) ContextCompat.getColor(requireContext(), R.color.primary)
            else ContextCompat.getColor(requireContext(), R.color.card_view)
        )

        binding.cardView2.setCardBackgroundColor(
            if (isSelectedCard2) ContextCompat.getColor(requireContext(), R.color.red)
            else ContextCompat.getColor(requireContext(), R.color.card_view)
        )

        binding.cardView3.setCardBackgroundColor(
            if (isSelectedCard3) ContextCompat.getColor(requireContext(), R.color.blue_light)
            else ContextCompat.getColor(requireContext(), R.color.card_view)
        )
    }
}
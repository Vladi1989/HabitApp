package com.spase_y.habittracker.main.navigation_fragment.a.recommend_habit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentRecommendHabitsBinding

class RecommendHabitsFragment : Fragment() {
    private var _binding: FragmentRecommendHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecommendHabitsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE

        val habits = listOf(
            Habit(
                "Вы лучший",
                "Скажите это себе, когда проснетесь",
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                "Пейте воду",
                "Выпивайте стакан воды утром",
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                "Двигайтесь",
                "Добавьте 10 минут упражнений в день",
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                "Планируйте",
                "Запланируйте свой день заранее",
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                "Будьте благодарны",
                "Напишите 3 вещи,\nза которые вы благодарны",
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            )
        )

        val adapter = RecommendHabitAdapter(habits)
        binding.rvRecommendHabit.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecommendHabit.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
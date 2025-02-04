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

        binding.ivArrowCrossBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        val habits = listOf(
            Habit(
                getString(R.string.habit_1_title),
                getString(R.string.habit_1_description),
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                getString(R.string.habit_2_title),
                getString(R.string.habit_2_description),
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                getString(R.string.habit_3_title),
                getString(R.string.habit_3_description),
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                getString(R.string.habit_4_title),
                getString(R.string.habit_4_description),
                R.drawable.ic_icon_general_video,
                R.drawable.ic_icon_general_enter
            ),
            Habit(
                getString(R.string.habit_5_title),
                getString(R.string.habit_5_description),
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
package com.spase_y.habittracker.main.navigation_fragment.a.create_new_habit.habit_name.habit_days

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.transition.Visibility
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentHabitDaysBinding


class HabitDaysFragment : Fragment() {
    private var _binding: FragmentHabitDaysBinding? = null
    private val binding get() = _binding!!

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
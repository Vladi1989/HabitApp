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
            binding.llMounthClose.visibility = View.VISIBLE
            binding.llMounthOpen.visibility = View.INVISIBLE
            binding.llYearOpen.visibility = View.INVISIBLE
        }
        binding.llmMonthClose.setOnClickListener {
            binding.llMounthClose.visibility = View.INVISIBLE
            binding.llMounthOpen.visibility = View.VISIBLE
            binding.llYearOpen.visibility = View.INVISIBLE
        }
        binding.llmYearClose.setOnClickListener {
            binding.llMounthClose.visibility = View.INVISIBLE
            binding.llMounthOpen.visibility = View.INVISIBLE
            binding.llYearOpen.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
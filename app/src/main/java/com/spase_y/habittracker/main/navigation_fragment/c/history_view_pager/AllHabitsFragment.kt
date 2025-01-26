package com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.databinding.FragmentAllHabitsBinding

class AllHabitsFragment : Fragment() {
    private var _binding: FragmentAllHabitsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllHabitsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = HabitsManger(getSharedPreferences())
        val habits = manager.getAllHabits()
        binding.tvHabitsCount.text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
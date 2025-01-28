package com.spase_y.habittracker.main.navigation_fragment.c.history_view_pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.spase_y.habittracker.MainActivity.Companion.getSharedPreferences
import com.spase_y.habittracker.data.HabitsManger
import com.spase_y.habittracker.databinding.FragmentAllHabitsBinding
import com.spase_y.habittracker.main.navigation_fragment.c.HistoryAdapter

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

        val habitsManager by lazy {
            HabitsManger(getSharedPreferences())
        }
        val habits = habitsManager.getAllHabits()

        val adapter = HistoryAdapter(habits)
        binding.rvHabits.adapter = adapter
        binding.rvHabits.layoutManager = LinearLayoutManager(requireContext())

        binding.tvHabitsCount.text = habits.size.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
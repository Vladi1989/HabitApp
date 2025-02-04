package com.spase_y.habittracker.start_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentStartABinding


class StartAFragment : Fragment() {
    private var _binding: FragmentStartABinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartABinding.inflate(inflater, container, false)
        setupNumberPickers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoToStartB.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainerView, StartBFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupNumberPickers() {
        // Настраиваем слушатели для пикеров
        binding.numberPickerHours.apply {
            minValue = 0
            maxValue = 23
            value = 8
            setOnValueChangedListener { _, _, newVal ->
                // Обработка изменения значения часов
                Log.d("Picker", getString(R.string.log_hours_selected, newVal))
            }
        }

        binding.numberPickerMinute.apply {
            minValue = 0
            maxValue = 59
            value = 30
            setOnValueChangedListener { _, _, newVal ->
                // Обработка изменения значения минут
                Log.d("Picker", getString(R.string.log_minutes_selected, newVal))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
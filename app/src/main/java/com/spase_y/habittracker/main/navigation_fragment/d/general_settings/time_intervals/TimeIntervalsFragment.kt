package com.spase_y.habittracker.main.navigation_fragment.d.general_settings.time_intervals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentTimeIntervalsBinding
import com.spase_y.habittracker.main.navigation_fragment.d.notification.MyBottomSheetTimeFragment

class TimeIntervalsFragment : Fragment() {
    private var _binding: FragmentTimeIntervalsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeIntervalsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        requireActivity().findViewById<View>(R.id.llNavigation)?.visibility = View.GONE


        setupButton(binding.textView9, 8, 14)  // Утро
        setupButton(binding.textView13, 14, 19) // День
        setupButton(binding.textView18, 19, 23) // Вечер
        setupButton(binding.textView20, 0, 8)  // Ночь
    }

    private fun setupButton(text: TextView, minHour: Int, maxHour: Int) {
        text.setOnClickListener {
            val bottomSheet = MyBottomSheetTimeFragment.newInstance(minHour, maxHour).apply {
                setCallback { selectedTime ->
                    text.text = selectedTime // Обновляем текст кнопки
                }
            }
            bottomSheet.show(parentFragmentManager, "MyBottomSheet")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.spase_y.habittracker.main.navigation_fragment.d.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivArrowBack1.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.llnotification5.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fcvMainApp, SoundEffectFragment())
                .addToBackStack(null)
                .commit()
        }

        setupDayButton(binding.showBottomSheetDay)
        setupDayButton(binding.showBottomSheetDay2)
        setupDayButton(binding.showBottomSheetDay3)
        setupDayButton(binding.showBottomSheetDay4)

        setupButton(binding.showBottomSheetButton, 0, 23) // Полный день
        setupButton(binding.showBottomSheetButton2, 8, 14) // Утро
        setupButton(binding.showBottomSheetButton3, 14, 19) // День
        setupButton(binding.showBottomSheetButton4, 19, 23) // Вечер
    }

    private fun setupButton(button: Button, minHour: Int, maxHour: Int) {
        button.setOnClickListener {
            val bottomSheet = MyBottomSheetTimeFragment.newInstance(minHour, maxHour).apply {
                setCallback { selectedTime ->
                    button.text = selectedTime // Обновляем текст кнопки
                }
            }
            bottomSheet.show(parentFragmentManager, "MyBottomSheet")
        }
    }

    private fun setupDayButton(button: Button) {
        button.setOnClickListener {
            val bottomDaySheet = MyBottomSheetDayFragment()
            bottomDaySheet.show(parentFragmentManager, "MyBottomSheet")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

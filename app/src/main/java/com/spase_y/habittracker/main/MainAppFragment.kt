package com.spase_y.habittracker.main

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.spase_y.habittracker.MainActivity
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentMainAppBinding
import com.spase_y.habittracker.main.navigation_fragment.a.MainFragmentAToday
import com.spase_y.habittracker.main.navigation_fragment.c.MainFragmentCHistory
import com.spase_y.habittracker.main.navigation_fragment.d.MainFragmentDMe
import com.spase_y.habittracker.main.navigation_fragment.b.recommend_your_way.MainFragmentBYourWay


class MainAppFragment : Fragment() {
    private var _binding: FragmentMainAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeFragment(MainFragmentAToday())

        // Инициализируем кнопки. Первая кнопка активная по умолчанию
        setActiveButton(binding.btnNavA, binding.tvNavA)

        // Устанавливаем обработчики кликов для всех кнопок
        binding.btnNavA.setOnClickListener {
            setActiveButton(binding.btnNavA, binding.tvNavA)
            changeFragment(MainFragmentAToday())
        }

        binding.btnNavB.setOnClickListener {
            setActiveButton(binding.btnNavB, binding.tvNavB)
            changeFragment(MainFragmentBYourWay())
        }

        binding.btnNavC.setOnClickListener {
            setActiveButton(binding.btnNavC, binding.tvNavC)
            changeFragment(MainFragmentCHistory())
        }

        binding.btnNavD.setOnClickListener {
            setActiveButton(binding.btnNavD, binding.tvNavD)
            changeFragment(MainFragmentDMe())
        }
    }

    private fun setActiveButton(activeImageView: ImageView, activeTextView: TextView) {
        // Сбрасываем состояние всех кнопок в "неактивное"
        resetButton(binding.btnNavA, binding.tvNavA)
        resetButton(binding.btnNavB, binding.tvNavB)
        resetButton(binding.btnNavC, binding.tvNavC)
        resetButton(binding.btnNavD, binding.tvNavD)

        // Активируем выбранную кнопку
        activeImageView.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.white),
            PorterDuff.Mode.SRC_IN
        )
        activeTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun resetButton(imageView: ImageView, textView: TextView) {
        imageView.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.gray),
            PorterDuff.Mode.SRC_IN
        )
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
    }
    fun openFragmentToday(selectedDay: Int) {
        setActiveButton(binding.btnNavA, binding.tvNavA)
        val fragment = MainFragmentAToday()
        fragment.arguments = bundleOf(Pair("Day", selectedDay))
        changeFragment(fragment)
    }

    fun changeFragment(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fcvMainApp,fragment)
            .addToBackStack(null)
            .commit()
    }
    companion object {
        fun Fragment.openFragmentToday(selectedDay: Int) {
            val mainFragment = requireActivity().supportFragmentManager.fragments[0]
            (mainFragment as MainAppFragment).openFragmentToday(selectedDay)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
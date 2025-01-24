package com.spase_y.habittracker.start_fragments

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentStartEBinding

class StartEFragment : Fragment() {
    private var _binding: FragmentStartEBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartEBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val circularProgressBar = binding.circularProgressBar
        val percentageText = binding.percentageText

        val animator = ValueAnimator.ofInt(1, 100)
        animator.duration = 2100  // Длительность анимации 3 секунды
        animator.addUpdateListener { animation ->
            val progress = animation.animatedValue as Int

            // Обновляем текст в процентах
            percentageText.text = "$progress%"
        }

        // Запускаем анимацию
        animator.start()

        circularProgressBar.apply {
            // Устанавливаем начальный прогресс
            progress = 0f
            // Устанавливаем анимацию с продолжительностью 3 секунды
            setProgressWithAnimation(100f, 3000)

            // Устанавливаем максимальный прогресс
            progressMax = 80f

            // Настройки цветов и других параметров
            progressBarColor = Color.RED
            progressBarColorStart = Color.BLUE
            progressBarColorEnd = Color.BLUE
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            backgroundProgressBarColor = Color.GRAY
            backgroundProgressBarColorStart = Color.GRAY
            backgroundProgressBarColorEnd = Color.GRAY
            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            progressBarWidth = 20f
            backgroundProgressBarWidth = 20f

            roundBorder = true
            startAngle = 180f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        // Установим слушатель, который будет реагировать на завершение прогресса
        circularProgressBar.onProgressChangeListener = { progress ->
            if (progress >= 65f) {
                // Когда прогресс достигает 65%, переходим на следующий фрагмент
                Handler(Looper.getMainLooper()).postDelayed({
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, StartFFragment())
                        .commit()
                }, 300) // Задержка 300 мс, чтобы немного подождать перед переходом
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
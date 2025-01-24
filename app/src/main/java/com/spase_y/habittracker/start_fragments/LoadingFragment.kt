package com.spase_y.habittracker.start_fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spase_y.habittracker.R
import com.spase_y.habittracker.databinding.FragmentLoadingBinding


class LoadingFragment : Fragment() {
    private var _binding: FragmentLoadingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.max = 1000
        val currentProgress = 1000
        ObjectAnimator.ofInt(binding.progressBar, "progress", currentProgress)
            .setDuration(2000)
            .start()

        // Переход через 2 секунды
        binding.progressBar.postDelayed({
            navigateToNextFragment()
        }, 2000)
    }

    private fun navigateToNextFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, StartAFragment()) // Переход на следующий фрагмент
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}